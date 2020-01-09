FROM java:8
VOLUME /tmp
WORKDIR /opt/NotepadPro/
ADD NotepadPro.jar NotepadPro.jar
EXPOSE 8080
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Xms512m", "-Xmx1g", "-jar", "NotepadPro.jar"]