# world-of-zuul
Trabalho para disciplina de Programação Orientada a Objetos



* COLAR ISSO DAQUI NO BOAS VINDAS PARA REALIZAR TESTE - REMOVER ISSO ANTES DE ENVIAR

        String nome = analisador.interagirComUsuario("Qual o seu nome?");
        char sexo = analisador.interagirComUsuario("Qual o seu gênero? (F ou M)").charAt(0);
        PersonagemPrincipal personagemPrincipal = new PersonagemPrincipal(nome,sexo);

        Npc novo = new Npc("Npc","Eu vi ele passando", 'f');
        Npc joaquim = new Npc("Joaquim",
                " ele gosta muito de frango e de coco seco. E ah, o frango pode ser com ou sem osso. Boa sorte!",
                'm');
        Npc alunaPPOO = new Npc("Aluna de PPOO","Pode ser que alguém tenha frango na sala da comp.", 'f',joaquim);
        System.out.println(novo.getDica(personagemPrincipal));
        System.out.println(alunaPPOO.getDica(personagemPrincipal));
        System.out.println(joaquim.getDica(personagemPrincipal));
        System.out.println(alunaPPOO.getDica(personagemPrincipal));