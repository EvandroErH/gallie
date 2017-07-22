function mascara(objeto, funcao) {
    v_obj = objeto
    v_fun = funcao
    setTimeout("execmascara()", 1)
}

function execmascara() {
    v_obj.value = v_fun(v_obj.value)
}

// Mascara
function valor(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/[0-9]{12}/, "inv\u00e1lido")   //limita pra m�ximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{8})$/, "$1.$2")  //coloca ponto antes dos �ltimos 8 digitos
    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2")  //coloca ponto antes dos �ltimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2")    //coloca virgula antes dos �ltimos 2 digitos
    return v;
}

// Mascara
function peso(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/[0-9]{12}/, "inv\u00e1lido")   //limita pra m�ximo 999.999.999,99
    //v=v.replace(/(\d{1})(\d{8})$/,"$1.$2")  //coloca ponto antes dos �ltimos 8 digitos
    //v=v.replace(/(\d{1})(\d{5})$/,"$1.$2")  //coloca ponto antes dos �ltimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,3})$/, "$1,$2")    //coloca virgula antes dos �ltimos 3 digitos
    return v;
}

function smi(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/[0-9]{12}/, "inv\u00e1lido")   //limita pra m�ximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2")    //coloca virgula antes dos �ltimos 2 digitos
    return v;
}

function fNumberPCP(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/[0-9]{12}/, "inv\u00e1lido")   //limita pra m�ximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{1,6})$/, "$1,$2")    //coloca virgula antes dos �ltimos 2 digitos
    return v;
}

// Mascara
function cnpj(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/^(\d{2})(\d)/, "$1.$2") //Coloca ponto entre o segundo e o terceiro d�gitos
    v = v.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3") //Coloca ponto entre o quinto e o sexto d�gitos
    v = v.replace(/\.(\d{3})(\d)/, ".$1/$2") //Coloca uma barra entre o oitavo e o nono d�gitos
    v = v.replace(/(\d{4})(\d)/, "$1-$2") //Coloca um h�fen depois do bloco de quatro d�gitos
    v = v.substring(0, 18)// DAT
    return v
}

// Mascara
function cpf(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/(\d{3})(\d)/, "$1.$2") //Coloca um ponto entre o terceiro e o quarto d�gitos
    v = v.replace(/(\d{3})(\d)/, "$1.$2") //Coloca um ponto entre o quinto e o sexto d�gitos
    v = v.replace(/(\d{3})(\d)/, "$1-$2") //Coloca um h�fen entre o 10� e o 11� d�gitos
    v = v.substring(0, 14)// DAT
    return v
}

// Mascara
function cep(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/^(\d{5})(\d)/, "$1-$2") //Esse � t�o f�cil que n�o merece explica��es
    v = v.substring(0, 9)// DAT
    return v
}

// Mascara
function mask_data(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/(\d{2})(\d)/, "$1/$2") //Coloca uma barra entre o segundo e o terceiro d�gitos
    v = v.replace(/(\d{2})(\d)/, "$1/$2") //Coloca uma barra entre o quarto e o quinto d�gitos
    // v=v.replace(/^[0-3]?\d\/[01]?\d\/(\d{2}|\d{4})$/)
    v = v.replace(/^((0[1-9]|[12]\d)\-(0[1-9]|1[0-2])|30\-(0[13-9]|1[0-2])|31\-(0[13578]|1[02]))\-\d{4}$/)
    // v=v.replace(/^(0[1-9]|[012][0-9]|3[01])/\-(0[1-9]|1[012])/\-([12][0-9]{3})/,"")
    v = v.substring(0, 10)// DAT
    return v
}

// Mascara MM/YYYY
function data2(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/(\d{2})(\d)/, "$1/$2") //Coloca uma barra entre o segundo e o terceiro d�gitos
    //v=v.replace(/(\d{2})(\d)/,"$1/$2") //Coloca uma barra entre o quarto e o quinto d�gitos
    // v=v.replace(/^[0-3]?\d\/[01]?\d\/(\d{2}|\d{4})$/)
    // v=v.replace(/^((0[1-9]|[12]\d)\-(0[1-9]|1[0-2])|30\-(0[13-9]|1[0-2])|31\-(0[13578]|1[02]))\-\d{4}$/)
    // v=v.replace(/^(0[1-9]|[012][0-9]|3[01])/\-(0[1-9]|1[012])/\-([12][0-9]{3})/,"")
    v = v.substring(0, 7)// DAT
    return v
}

// Mascara
function hora(v) {
    // v=v.replace(/\D/g,"") //Remove tudo o que n�o � d�gito
    v = v.replace(/(\d{2})(\d)/, "$1:$2") //Coloca dois ponto entre o segundo e o terceiro d�gitos
    // v = v.replace((/[012][0-9]:[0-5][0-9]/), "")
    // if v.teste( (/[012][0-9]:[0-5][0-9]/);"certo";"errado")
    v = v.substring(0, 5)// DAT
    return v
}

// Mascara
function soLetras(v) {
    return v.replace(/\d/g, "") // Remove tudo o que n�o � Letra
}

// Mascara
function soLetrasMA(v) {
    v = v.toUpperCase() // Mai�sculas
    return v.replace(/\d/g, "") // Remove tudo o que n�o � Letra ->maiusculas
}

// Mascara
function soLetrasMI(v) {
    v = v.toLowerCase() // Minusculas
    return v.replace(/\d/g, "") // Remove tudo o que n�o � Letra ->minusculas
}

// Mascara
function soNumeros(v) {
    return v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
}

// Mascara
function telefone(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/^(\d\d)(\d)/g, "($1) $2") //Coloca par�nteses em volta dos dois primeiros d�gitos
    v = v.replace(/(\d)(\d{4})$/, "$1-$2") //Coloca h�fen entre o quarto e o quinto d�gitos
    return v
}
// Mascara
function telefoneSimples(v) {
    v = v.replace(/\D/g, "") // Remove tudo o que n�o � d�gito
    v = v.replace(/(\d{4})(\d)/, "$1-$2") //Coloca h�fen esntre o quarto e o quinto d�gitos
    return v
}

// Mascara
function romanos(v) {
    v = v.toUpperCase() // Mai�sculas
    v = v.replace(/[^IVXLCDM]/g, "") //Remove tudo o que n�o for I, V, X, L, C, D ou M
    //Essa � complicada! Copiei daqui: http://www.diveintopython.org/refactoring/refactoring.html
    while (v.replace(/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/, "") != "")
        v = v.replace(/.$/, "")
    return v
}

function email(v) {
    return v.toLowerCase();
}

// Mascara
function site(v) {
    v = v.replace(/^http:\/\/?/, "")
    dominio = v
    caminho = ""
    if (v.indexOf("/") > -1)
        dominio = v.split("/")[0]
    caminho = v.replace(/[^\/]*/, "")
    dominio = dominio.replace(/[^\w\.\+-:@]/g, "")
    caminho = caminho.replace(/[^\w\d\+-@:\?&=%\(\)\.]/g, "")
    caminho = caminho.replace(/([\?&])=/, "$1")
    if (caminho != "")
        dominio = dominio.replace(/\.+$/, "")
    v = "http://" + dominio + caminho
    v = v.toLowerCase();
    return v
}