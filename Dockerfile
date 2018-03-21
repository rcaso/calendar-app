FROM airhacks/glassfish
COPY ./target/calendar-app.war ${DEPLOYMENT_DIR}
