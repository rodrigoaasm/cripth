<h1>Cripth</h1>

Cripth is a small project used in my study of cryptography. It is based on creating modules for storing keys and for encrypting messages exchanged between hosts.

The development of the modules is based on the strategy of the HTTPS protocol, which initiates the exchange of messages, exchanges public keys (certificates) that are in RSA 1028 bits. For communication, an AES-128 key is generated which encrypts the message and then the AES key is encrypted by the public key of the host to which it wishes to communicate.

NOTE: AES-256 is used in HTTPS other than the modules developed here that use AES-128.


<h1>Cripth (PT-br)</h1>
<p>O Cripth é uma pequeno projeto utilizado no meu estudo de criptografias. Baseia-se em criar módulos para o 
armazenamento de chaves e para criptografar mensagens trocadas entre hosts. </p>
<p>O desenvolvimento dos módulos é baseado na estratégia do protocolo HTTPS, que para iniciar a troca de mensagens, efetua troca
de chaves publicas (certificados) que estão em RSA 1028 bits. Para a comunicação é gerada um chave AES-128 a qual faz a criptografia
da mensagem e em seguida a chave AES é criptografada pela chave publica do host ao qual deseja se comunicar. </p>

<p>OBS.: Em HTTPS é utilizado AES-256 diferente dos módulos aqui desenvolvidos que utilizam AES-128. </p>

