1) GET DOCKER CONTAINER FOR WEBAPP
*********************************************
docker pull nginx

docker-compose -f docker-compose.nginx.yml up

2) DON`T FORGET TO DO THE STEPS IN README.md
*********************************************

3) OPEN INTELLIJ
*********************************************
A) Navigate via Projectexplorer to

./apps/webApp/build.gradle

Wait for indexing of intellij and then start the task "buildImage"


B) Use right side navbar and navigate e.g.

./apps/UserManagement/app/Tasks/build/

run "build"

C) navigate now

./apps/UserManagement/app/Tasks/liberty/

run "libertyRun"



