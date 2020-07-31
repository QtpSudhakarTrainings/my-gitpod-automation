FROM gitpod/workspace-full-vnc


USER root

# Install dependencies
RUN apt-get update                                             \
    && apt-get install -y libgtk-3-dev firefox                 \
    && apt-get sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' \
    && apt-get update                                           \
    && apt-get install -y libgtk-3-dev google-chrome-stable    \
    && apt-get clean && rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/*


USER gitpod


#RUN npm install -g cordova ionic qrcode @ionic/lab cordova-res native-run @capacitor/core @capacitor/cli electron  electron-packager  

    



USER root
