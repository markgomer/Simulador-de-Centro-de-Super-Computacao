# Trabalho 3 - Centro de Supercomputação

*Grupo:*

-
-
-
-
-

## Enunciado

Você foi contratado(a) para criar um sistema de acesso a um Centro de Supercomputação (CSC) equipado com um computador Frontier da HPLinks para um site externo. que custa 600 milhões de dólares. Atualmente, há duas empresas concorrentes A e B, cada uma com 10 funcionários que pagaram para acessar o CSC. As regras acordadas pelas empresas e pelo proprietário do CSC são as seguintes:

Quando ninguém estiver conectado usando o CSC, um funcionário de qualquer uma das empresas A ou B pode ser o primeiro a acessar o sistema.
Se alguém da empresa A já estiver usando o CSC, somente outros funcionários de A podem começar a acessá-lo também. Funcionários da empresa B deverão ficar bloqueados aguardando até que todos os funcionários de A terminarem os seus acessos. (E vice-versa com relação aos funcionários de B.)
O número máximo de funcionários de uma mesma empresa simultaneamente acessando o CSC é 3.
Para cada funcionário que tenta acessar o CSC, um thread é criado. Se pode acessar, o thread "prossegue" sua operação. Caso contrário, fica bloqueado aguardando a liberação do acesso.

Usando semáforos e threads, escreva em Java um programa que simule o acesso de 10 funcionários de cada empresa.

## Detalhes de implementação
Para simular tentativas de conexões dos funcionários das empresas, crie 10 threads de cada empresa (20 threads ao todo) sendo que cada um aguarda um tempo aleatório antes de tentar o acesso ao CSC.
A simulação do "uso" do CSC corresponde a fazer o thread conectado aguardar por um tempo aleatório entre 5 e 10 segundos (Thread.sleep()), desconectando em seguida.

### Exemplo de saída esperada na tela
(pode variar conforme temporização e outras condições)

```
F1 [A] tentando acesso
+ F1 [A] acessou
F3 [B] tentando acesso
F5 [A] tentando acesso
+ F5 [A] acessou
F1 [B] tentando acesso
F2 [A] tentando acesso
+ F2 [A] acessou
F3 [A] tentando acesso
- F1 [A] terminou acesso
+ F3 [A] acessou
- F2 [A] terminou acesso
- F5 [A] terminou acesso
- F3 [A] terminou acesso
+ F3 [B] acessou
+ F1 [B] acessou
...
```

**Envio:**

Envie apenas os arquivos .javacorrespondentes ao seu programa. Pode ser feito em equipes de até 5 estudantes.

Todos da equipe devem postar os mesmos arquivos (incluir comentário no arquivo do programa principal – main com os nomes dos participantes da equipe).

O trabalho deverá ser apresentado ao professor na data especificada.