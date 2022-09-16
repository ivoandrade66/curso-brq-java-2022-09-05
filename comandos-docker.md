# Principais comandos

## Como iniciar um container (que ainda nao existe)

...
    docker run NOME-DA IMAGEM   

    docker run

    docker run docker/getting-started

## ver os meus containers que estão em execucao
...

    docker ps

## para ver todos os docker que tenho inclusive os parados

    docker ps -a
...
## se eu quiser parar a execucao de um contaner:
...
    docker stop NOMEDOCONTAINER
    EX. docker stop funny_kepler
...
## se eu quiser iniciar um container que já existe:
...
    docker start NOMEDOCONTAINER
    EX. docker start funny_kepler
...
## para remover um container:
...
    Obs: o container deve estar parado

    docker rm NOMEDOCONTAINER

    EX. docker rm funny_kepler
...
## eu posso estipular o nome do container:
...
    docker run  --name NOMEDOCONTAINER NOME DA IMAGEM
    Ex. docker run -- name hello-word docker/getting-started

Obs: exemplo na criação do container


docker run -d -p 80:80 docker/getting-started
