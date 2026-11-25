Requisitos para rodar o projeto: Java 17 e MySQL.
- Criar um banco de dados no MySQL com o nome series.
- Importar o projeto no Eclipse como um projeto Maven.
- No arquivo application.properties, ajustar o usuário e a senha de acordo com as configurações do MySQL da máquina. (testado como root e senha em branco)
- Executar a classe principal do projeto como uma aplicação Spring Boot.

Rotas para páginas do sistema

Lista de clientes: http://localhost:8080/paginas/tutores
Cadastro de clientes: http://localhost:8080/paginas/tutores/nova
Lista de animais: http://localhost:8080/paginas/animais
Cadastro de animais: http://localhost:8080/paginas/animais/nova


1- O serviço no meio
Implementadas classes TutorService e AnimalService, seguindo o exemplo do projeto Series. Os controllers passaram a chamar os serviços em vez de acessar diretamente os DAOs.

2- Cliente com bicho não sai
Validação antes de remover um cliente. Caso tenha algum animal cadastrado, é lançada uma RegraDeNegocioException e uma mensagem é apresentada na página, informando que o cliente não pode ser removido. Caso não tenha animais, a remoção ocorre normalmente.

3- Um telefone, um cliente
Criada uma consulta no TutorDAOJPA para verificar se um telefone já está cadastrado. 
Caso o telefone já pertença a outro cliente, é apresentada uma mensagem de erro no formulário

4- A ficha do cliente
Na lista de clientes, o nome de cada cliente passou a ser um link que leva para sua página de detalhes, onde são apresentados o nome, o telefone e os animais cadastrados
Para buscar os animais, foi implementado o método listarPorTutor no DAO de animais, utilizando uma consulta JPQL que filtra pelo ID do tutor, seguindo a lógica de consulta das séries.

Quanto aos commits em cada passo acabei esquecendo de realizar, tive que subir o andamento do projeto em um commit único 