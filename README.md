Projeto de Gerenciamento de Cursos e Professores

Este projeto foi desenvolvido para gerenciar informações sobre cursos e professores em uma instituição educacional. Ele inclui funcionalidades para adicionar, atualizar, listar e excluir cursos e professores, além de permitir que os alunos sejam matriculados em cursos e que professores sejam designados a cursos específicos.

Tecnologias Utilizadas
Java
JDBC (Java Database Connectivity)
PostgreSQL (Sistema de Gerenciamento de Banco de Dados)
Git (Controle de Versão)
IDE (Ambiente de Desenvolvimento Integrado) de sua escolha
Estrutura do Projeto
O projeto é dividido em pacotes com responsabilidades específicas:

com.MiniCurso.model: Contém as classes que representam os modelos de dados, como Student, Teacher e Course.

com.MiniCurso.dao: Responsável pela comunicação com o banco de dados. Aqui estão as classes que realizam operações de CRUD (Create, Read, Update, Delete) para os modelos de dados.

com.MiniCurso.util: Contém a classe ConnectionFactory para estabelecer conexão com o banco de dados.

com.MiniCurso.Main: Classe principal onde o programa é executado. Aqui estão os métodos para interação com o usuário e chamadas aos métodos DAO.

Configuração do Banco de Dados
Antes de executar o projeto, certifique-se de configurar o banco de dados PostgreSQL com as seguintes informações:

Nome do banco de dados: mini_curso_db
Usuário: seu_usuario
Senha: sua_senha
Você pode modificar essas configurações no arquivo ConnectionFactory.java localizado no pacote com.MiniCurso.util.

Executando o Projeto
Clone o repositório do projeto para o seu ambiente local usando o comando git clone.

Abra o projeto em sua IDE preferida.

Certifique-se de ter o PostgreSQL instalado e o banco de dados configurado conforme as informações acima.

Execute a classe Main.java para iniciar a aplicação.

Funcionalidades Principais
Cadastro de Alunos:

Adicionar novos alunos com matrícula, nome, e-mail e CPF.
Cadastro de Professores:

Adicionar novos professores com matrícula, nome, e-mail e CPF.
Cadastro de Cursos:

Adicionar novos cursos com ID, nome e carga horária.
Matrícula de Alunos em Cursos:

Matricular alunos em cursos existentes.
Designação de Professores a Cursos:

Designar professores para cursos específicos.
Atualização e Exclusão de Dados:

Atualizar e excluir informações de alunos, professores e cursos.
Contribuições
Sinta-se à vontade para contribuir com melhorias neste projeto. Você pode adicionar novas funcionalidades, aprimorar a interface do usuário, implementar testes automatizados, entre outras melhorias.

Para contribuir:

Faça um fork do repositório.
Crie uma branch para suas alterações (git checkout -b feature/nova-feature).
Commit suas mudanças (git commit -am 'Adicionar nova feature').
Push para a branch (git push origin feature/nova-feature).
Crie um novo Pull Request.
Considerações Finais
Este projeto é uma demonstração simples de como você pode criar um sistema de gerenciamento de cursos e professores em Java usando JDBC e PostgreSQL. Sinta-se à vontade para explorar e expandir suas funcionalidades de acordo com suas necessidades.

Espero que este README tenha sido útil para entender a estrutura e funcionamento básico do projeto. Em caso de dúvidas ou sugestões, não hesite em entrar em contato.

Obrigado por utilizar este projeto! 🚀
