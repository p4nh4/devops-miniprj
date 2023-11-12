// Generate nginx configuration
        def nginxConfig = """
        server {
            listen 80;
            server_name your_domain_name.com;

            location / {
                proxy_pass http://localhost:${selectedPort};
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
            }
        }
        """

        // Save nginx configuration file
        writeFile file: "/etc/nginx/conf.d/mydomain.conf", text: nginxConfig
        sendTelegramMessage("Nginx configuration updated for port ${selectedPort}", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)

        // Restart nginx
        sh "sudo service nginx restart"
        sendTelegramMessage("Nginx restarted successfully", TELEGRAM_BOT_TOKEN, TELEGRAM_CHAT_ID)
