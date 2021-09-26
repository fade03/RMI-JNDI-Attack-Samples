# RMI-JNDI-Attack-Samples
The samples of RMI&JNDI attack
RPC Demo: https://www.wolai.com/jyw_me/qECVxm3MQZ6G5fk7xKTbep?theme=light

## RMI
- [Client Attack Server](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/CAS)
- [Server Attack Client](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/SAC)
- [Registry Attack Client](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/RAC2S)
- [Registry Attack Server](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/RAC2S)
- [Client Attack Registry](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/CAR)
- [Server Attack Registry](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/SAR)
- [Bypass JEP290 8u121~8u230](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/bypassJEP290/jdk121_230)(`DGCClient#dirty`)
- [Bypass JEP290 8u231~8u240](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/RMI/bypassJEP290/jdk231_241)(`UnicastRemoteObject#readObject`)

## JNDI
- [RMI Server](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/JNDI/rmiMethod)
- [LDAP Server](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/JNDI/LDAPMethod)
- [High version bypass](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/JNDI/bypass1)(The LDAP server returns malicious serialized data)
- [High version bypass](https://github.com/F4ded/RMI-JNDI-Attack-Samples/tree/master/src/main/java/JNDI/bypass2)(Using The local factory class `org.apache.naming.facotry.BeanFactory` to execute an EL expression)
