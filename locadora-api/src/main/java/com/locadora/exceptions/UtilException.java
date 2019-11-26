package com.locadora.exceptions;

public class UtilException {

    private static String CAMPO_PREENCHIMENTO_OBRIGATORIO = "Campo preenchimento obrigatório: ";
    public static String USUARIO_NULL_OU_VAZIO = CAMPO_PREENCHIMENTO_OBRIGATORIO + "Usuário";
    public static String FILME_NULL_OU_VAZIO = CAMPO_PREENCHIMENTO_OBRIGATORIO + "Filme";
    public static String FILME_SEM_ESTOQUE = "Filme sem estoque!";
}
