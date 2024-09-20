```markdown
# Passos Necessários para Rodar o Projeto 🚀

Após clonar o projeto para sua máquina.

## 1. Abra o terminal e execute o comando mvn:

mvn clean package

## 2. Crie a imagem do Docker

Na pasta raiz da aplicação, abra o terminal e rode o seguinte comando para criar a imagem do Docker:

```bash
docker-compose up --build -d
```

Esse comando vai compilar o projeto e subir os contêineres em segundo plano.

## 3. Verifique se o contêiner está rodando

Após rodar o comando acima, digite o seguinte para confirmar se está tudo certo:

```bash
docker ps
```

## 4. Após a aplicação em execução, execute os seguintes cURLs

### 4.1 - Criação dos times:

```bash
curl --location --request POST 'http://localhost:9000/api/team-operations' \
--header 'Content-Type: application/json' \
--data-raw '{
    "operationTypes": [
        {
            "operationType": "CARD"
        },
        {
            "operationType": "LOANS"
        },
        {
            "operationType": "OTHER_MATTERS"
        }
    ]
}'
```

### 4.2 - Criação dos atendentes vinculados aos times:

```bash
curl --location --request POST 'http://localhost:9000/api/attendant' \
--header 'Content-Type: application/json' \
--data-raw '[
    {
        "name": "Goku",
        "operationRequest": {
            "operationType": "CARD"
        }
    },
    {
        "name": "Titi",
        "operationRequest": {
            "operationType": "CARD"
        }
    },    
    {
        "name": "Bulma",
        "operationRequest": {
            "operationType": "LOANS"
        }
    },    
    {
        "name": "Trunks",
        "operationRequest": {
            "operationType": "OTHER_MATTERS"
        }
    }
]'
```

### 4.3 - Criação de solicitação:

```bash
curl --location --request POST 'http://localhost:9000/api/request' \
--header 'Content-Type: application/json' \
--data-raw '{
    "subject": "Outros assuntos"
}'
```

Após atingir o total de **três solicitações por atendente**, na quarta tentativa será exibida a mensagem:

```json
{
    "message": "Sem atendentes disponíveis no momento."
}
```

A solicitação será salva no banco de dados com o status de **OPEN** (aberto). A aplicação possui um **schedule** que executa a cada **1 minuto**, com o objetivo de obter todas as solicitações em aberto e redistribuí-las para os atendentes disponíveis.

Para que os atendentes com solicitações em andamento (status **IN_PROGRESS**) fiquem disponíveis novamente, é necessário fechar a solicitação que está em progresso. Abaixo está o endpoint para buscar as solicitações de acordo com os status: **OPEN**, **IN_PROGRESS** e **COMPLETED**:

### 4.4 - Buscar solicitação a partir do status:

```bash
curl --location --request GET 'http://localhost:9000/api/request?statusRequest=IN_PROGRESS&page=0&size=5'
```

Após buscar as solicitações, obtenha o ID e chame o endpoint que irá finalizar a solicitação:

### 4.5 - Finalizar solicitação:

```bash
curl --location --request PUT 'http://localhost:9000/api/request/{requestId}/finalize'
```

Remover o parâmetro {requestId} e substituir pelo id da solicitação que deseja finalizar, ao executar o status mudará de
**IN_PROGRESS** para **COMPLETED** e o atendente vai ser associado a solicitação que estiver aberta na base de dados(que será vinculada após o **schedule** automático 
for executado a cada **1 minuto**) ou a uma novamente solicitação que for enviada pelo endpoint de criação de solicitação.
### 4.6 - Buscar todos os atendentes

Nesse endpoint, será possível visualizar todos os atendentes e a quantidade de solicitações em progresso que cada um possui:

```bash
curl --location --request GET 'http://localhost:9000/api/attendant/request-count'
```