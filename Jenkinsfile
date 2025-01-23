pipeline {
    agent any

    // Thêm block `parameters` để khai báo tham số
    parameters {
        string(name: 'BUILD_VERSION', defaultValue: '1.0.0', description: 'Version number for the build')
        booleanParam(name: 'SEND_NOTIFICATION', defaultValue: false, description: 'Send notification email after build')
        booleanParam(name: 'REBUILD', defaultValue: false, description: 'Rebuild code')
        string(name: 'BUILD', defaultValue: 'test', description: 'Build server test in linux')
    }

    environment {
        // Cấu hình đường dẫn đến Maven và JDK
        MAVEN_HOME = 'C:\\mvn'  // Đường dẫn đến thư mục Maven, không bao gồm thư mục 'bin'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'  // Đường dẫn đến JDK 17
        CURL_HOME = 'C:\\curl\\bin'  // Đường dẫn đến thư mục chứa curl
        SSHPASS_HOME = 'C:\\ssh'  // Đường dẫn đến thư mục chứa curl
        SH_HOME = 'C:\\Program Files\\Git\\bin' // Đường dẫn sh
        PATH = "${JAVA_HOME}\\bin;${MAVEN_HOME}\\bin;${CURL_HOME};${SSHPASS_HOME};${SH_HOME};${env.PATH}"  // Thêm Maven, JDK và cURL vào PATH

        // SSH và thông tin VPS B
        SSH_CREDENTIALS_ID = 'vps-linux'
        VPS_HOST = '128.199.252.137'
        REMOTE_PATH = '/root/nro/'  // Thư mục trên VPS B
        LOCAL_DIR = './target'  // Thư mục `target` sau build
        KEY_PATH = 'C:\\Users\\thtung\\.ssh\\id_rsa'
    }

    stages {
        stage('Set Build Display Name') {
            steps {
                script {
                    // Thêm tham số BUILD_VERSION vào tên của build
                    currentBuild.displayName = "${params.BUILD_VERSION}"
                }
            }
        }
        
        stage('Parallel Tasks: Check Environment') {
            parallel {
                stage('Check Current Directory') {
                    steps {
                        echo 'Checking current working directory on Windows...'
                        bat 'echo Current Directory: && cd' // Hiển thị thư mục hiện tại
                        bat 'echo Listing contents of the current directory: && dir' // Hiển thị nội dung thư mục
                    }
                }
                stage('Check Java and Maven versions') {
                    steps {
                        echo 'Checking Java and Maven versions...'
                        bat 'java -version' // Kiểm tra phiên bản JDK
                        bat 'mvn -v'        // Kiểm tra phiên bản Maven
                    }
                }
            }
        }
        
        stage('Build with Maven') {
            when {
                expression { params.REBUILD } // Thực hiện stage này nếu BUILD_TEST là true
            }
            steps {
                echo 'Building the project using Maven...'
                // Thực hiện lệnh Maven để build project trên Windows
                bat "mvn clean package -Dversion=${params.BUILD_VERSION}" // Lệnh Maven cho Windows
            }
        }

        stage('Zip the Build using 7-Zip') {
             when {
                expression { params.BUILD == 'both' || params.BUILD == 'pro' }
            }
            steps {
                echo 'Zipping the build files using 7-Zip...'
                // Sử dụng 7-Zip để nén thư mục 'target' thành file 'build.zip'
                bat "\"C:\\Program Files\\7-Zip\\7z.exe\" a develop-${params.BUILD_VERSION}.zip target" // Đường dẫn đến 7z.exe
            }
        }

        stage('Upload to FTP') {
             when {
                expression { params.BUILD == 'both' || params.BUILD == 'pro' }
            }
            steps {
                script {
                    // Cấu hình thông tin FTP
                    def ftpDetails = [
                        url      : 'ftp://128.199.252.137:21',  // URL của server FTP
                        username : 'administrator',                // Tên người dùng FTP
                        password : '12345678@Abc',                // Mật khẩu của FTP
                        remoteDir: ''             // Đường dẫn thư mục trên FTP
                    ]
                    // Sử dụng lệnh curl để đẩy file 'build.zip' lên server FTP
                    bat """
                        curl --ftp-port - -T develop-${params.BUILD_VERSION}.zip -u ${ftpDetails.username}:${ftpDetails.password} ${ftpDetails.url}${ftpDetails.remoteDir}
                    """
                }
            }
        }

        stage('Upload Build Directory to VPS LINUX') {
            when {
                expression { params.BUILD == 'both' || params.BUILD == 'test' }
            }
            steps {
                echo 'Uploading build directory (target) to VPS B...'
                bat '''
                    scp -v -r -i C:\\Users\\thtung\\.ssh\\id_rsa -o StrictHostKeyChecking=no target root@128.199.252.137:/root/nro/pico
                '''
            }
        }

        stage('Maintain in VPS LINUX') {
            when {
                expression { params.BUILD == 'both' || params.BUILD == 'test' }
            }
            steps {
                echo 'Maintain in VPS B...'
                bat '''
                    ssh -T -i C:\\Users\\thtung\\.ssh\\id_rsa -o StrictHostKeyChecking=no root@128.199.252.137 "docker exec -i pico_app tmux send-keys -t pico_session 'baotri' C-m"
                '''
            }
        }

        stage('Wait Maintain') {
            steps {
                echo "Starting wait for 10 seconds..."
                sleep 20
                echo "Finished waiting!"
            }
        }

        stage('Deploy on VPS LINUX') {
             when {
                expression { params.BUILD == 'both' || params.BUILD == 'test' }
            }
            steps {
                echo 'Deploy on VPS B...'
                bat '''
                    ssh -i C:\\Users\\thtung\\.ssh\\id_rsa -o StrictHostKeyChecking=no root@128.199.252.137 "cd nro && docker compose up --build -d"
                '''
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            script {
                if (params.SEND_NOTIFICATION) {
                    sendEmailNotification("Build Success: ${env.JOB_NAME} - Version ${params.BUILD_VERSION}", "success")
                }
            }
        }
        failure {
            script {
                if (params.SEND_NOTIFICATION) {
                    sendEmailNotification("Build Failed: ${env.JOB_NAME} - Version ${params.BUILD_VERSION}", "failure")
                }
            }
        }
    }
}

// Hàm dùng chung để gửi email
def sendEmailNotification(subject, status) {
    def color = (status == "success") ? "#4CAF50" : "#e74c3c"
    def title = (status == "success") ? "Build Successful!" : "Build Failed"
    def body = """
        <html>
        <head>
            <style>
                body { font-family: Arial, sans-serif; color: #333; }
                h2 { color: ${color}; }
                .content { background-color: #f4f4f4; padding: 20px; border-radius: 5px; }
                .build-info { font-size: 14px; margin-top: 10px; }
                .build-info p { margin: 5px 0; }
                .changes ul { list-style-type: disc; margin-left: 20px; }
                .footer { font-size: 12px; color: #888; margin-top: 20px; }
            </style>
        </head>
        <body>
            <div class="content">
                <h2>${title}</h2>
                <p>The build for project <strong>${env.JOB_NAME}</strong> has ${status}.</p>
                <div class="build-info">
                    <p><strong>Project:</strong> ${env.JOB_NAME}</p>
                    <p><strong>Build Number:</strong> ${env.BUILD_NUMBER}</p>
                    <p><strong>Build Version:</strong> ${params.BUILD_VERSION}</p>
                </div>
                <div class="changes">
                    <p><strong>Changes in this Build:</strong></p>
                    <ul>${currentBuild.changeSets.collect { changeSet ->
                        changeSet.items.collect { item -> "<li>${item.msg} by ${item.author}</li>" }.join("")
                    }.join("")}</ul>
                </div>
                <p><strong>Build URL:</strong> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
            </div>
            <div class="footer">
                <p>This is an automated email from Jenkins. Please do not reply to this email.</p>
            </div>
        </body>
        </html>
    """

    emailext(
        to: 'huytung55123@gmail.com, phamtuanh2000@gmail.com',
        subject: subject,
        body: body,
        mimeType: 'text/html',
        from: 'noreply@nrohat.com',
        replyTo: 'noreply@nrohat.com',
        recipientProviders: [[$class: 'RequesterRecipientProvider']]
    )
}
