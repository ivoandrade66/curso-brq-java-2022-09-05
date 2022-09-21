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
# Eu posso estipular o nome de um container

Obs: exemplo na criação do container
O nome da imagem sempre precisa ser o último parâmetro do docker run

```
    docker run  --nane NOMEDOCONTAINERDESEJO NOMEDAIMAGEM
    Ex: docker run --name hello-world docker/getting-started
```

# redirecionar a requisição da máquina hospedeira para um container docker

Obs: exemplo na criação do container


```
    docker run  --name NOMEDOCONTAINERDESEJO -p PORTA-HOSPEDEIRO:PORTA-CONTAINER  NOMEDAIMAGEM

    docker run  --name NOMEDOCONTAINERDESEJO -p PORTA-EXTERNA:PORTA-INTERNA  NOMEDAIMAGEM

    Ex: docker run --name hello-world -p 80:80  docker/getting-started

    docker run --name hello-word -p 80:80 -p 8000:80 docker/getting-started
```

# Como podemos acessar o terminal de um container?

Obs: o container deve estar em execução 

```

    docker exec -it NOMEDOCONTAINER /bin/bash

    /bin/bash (terminal) é o comando que vamos executar quando ao entrar no container 
    -it -> 'modo interativo'

    Ex: docker exec -it hello-word /bin/bash
```