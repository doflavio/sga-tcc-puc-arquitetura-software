# sga-tcc-puc-arquitetura-software
Projeto TCC de uma poc de um Sistema de Gestão Ambiental - Pós Arquitetura de software Distribuído

docker run -it --name sigam -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management


sg-config-server - Porta: 8888
sg-eureka-server - Porta: 8761
sga-gateway-zuul - Porta: 8765
sga-oauth        - Porta: 8081
sg-user          - Porta: 8082
sg-notificacao   - Porta: 8084
sg-sensor        - Porta: 8085
sg-monitoramento-seguranca - Porta:8083

{{URL-sg-config-server}}
{{URL-sg-eureka-server}}
{{URL-sga-gateway-zuul}}
{{URL-sga-oauth}}
{{URL-sg-user}}
{{URL-sg-monitoramento-seguranca}}
{{URL-sg-notificacao}}
{{URL-sg-sensor}}

----------------------------------------------------------
Autenticação e autorização
Para autenticar, realizar o login utilizar  microsserviço: sga-oauth. 
Ao Enviar na requisição o tipo de atutorização basic com client-name e client-secre e no corpo da requisição o usernam e o password e grant_tpype = passwork, o microsserviço vai gerar o token jwt para ser enviado nas requisições posteriores. Para gerar o token o Microsserviço sg-user deve estar no ar. Pois os registros dos usuários é são incluídos no banco de dados do microsserviço usuários.

O serviço sga-oauth faz o trabalho do autorization serve, mas a autorização é realizada pelo gateway