$(document).ready(function () {

    //Array para controlar os paineis de imagens a serem exibidas para o usuário
    section = ["sujeito", "verbo", "tempo", "complemento"];

    //Variavel para controle de paineis
    sec = 0;

    // Valor da linha ativa no sistema de varredura
    row = 0;

    // Valor da Coluna ativa no sistema de varredura
    col = 0;
    
    
    $images = null;
    $div = null;
    
    numRows = 0;
    numCols = 0;

    // Loop para seleção de linhas.
    var timeRow;

    // Loop para seleção de imagens.
    var timeCol;

    init();
    /**
     * Função que inicialida a tela de a cordo com a sessão definida na variável 
     * 
     */
    function init() {
        $("#row").attr("value", 0);
        $("#col").attr("value", 0);
        
        //--------------------------------------
        $images = $("#" + section[sec] + " img");
        $div = $("#"+ section[sec]);
        
        $images.css("border", "solid 4px transparent"); 
        
        for (i = 0; i <= 3; i++) {
            if (i != sec) {
                $("#" + section[i]).hide();
            } else {
                $($div).show();
                $($div).children().css("border", "solid 4px transparent");
                searchRow($($div).children());
            }
        }
    }

    /**
     * 
     * @param {array} $rows - Array de divs que representam linha da tela de
     * varredura.
     * @returns {undefined}
     */
    function searchRow($rows) {
        row = 1;
        timeRow = setInterval(function () {
            if (row > $rows.size() + 1) {
                row = 1;
            } else {
                $("#row").attr("value", row);
                $($rows[row - 2]).css("border", "solid 4px transparent");
                $($rows[row - 1]).css("border", "solid 4px rgba(255,200,0,0.8)");
                row++;
            }
        }, 3000 / (1 + ($('#velocidade').val()*3/100)));
    }
    
    /**
     * 
     * @param {type} $cols  - Array de divs que representam colunas da tela de
     *  varredura. ( + 1)
     * @returns {undefined}
     */
    function searchCol($cols) {
        col = 1;
        timeCol = setInterval(function () {
            if (col > $cols.size() + 1) {
                col = 1;
            } else {
                $("#col").attr("value", col);
                $($cols[col - 2]).css("border", "solid 4px transparent");
                $($cols[col - 1]).css("border", "solid 4px rgba(0,0,200,0.8)");
                col++;
            }
         }, 3000 / (1 + ($('#velocidade').val()*3/100)));
    }

    /**
     * Função para verificar a seleção de linhas e colunas
     * 
     */
    function verificaClick() {
        if ($("#col").val() == 0) {
            if($("#row").val() > 0 && $("#row").val() <= $($div).children().size()){
                clearInterval(timeRow);
                $images = $("#"+section[sec]).children()[row - 2];
                searchCol($($images).children("img"));
            } 
        } else{
            if($("#col").val() > 0 && $("#col").val() <= $($images).children().size()){
                clearInterval(timeCol);
                if (sec == 1) {
                    if($("#row").val() == 1){
                        if($("#col").val() == 2){
                            window.location.reload();
                        }else if($("#col").val() == 3){
                            window.location.replace("/Comunicador/index.xhtml");
                        }
                    }else{
                        sec ++;
                    }
                }else{
                    if (sec == 3) {
                        if($("#row").val() == 1){
                            if($("#col").val() == 2){
                                window.location.reload();
                            }else if($("#col").val() == 3){
                                window.location.replace("/Comunicador/index.xhtml");
                            }
                        }
                        sec = 3;
                    }else{
                        sec++;
                    }
                }
                init();
            }
        }
    }

    /**
     * Evento que é disparado quando o elemento é clicado que logo é chamado a
     * verificação de click.
     */
    $("#form").click(verificaClick);

    /**
     * Evendo que é disparado quando o navegador retorna erro das imagens não 
     * encontradas, substituindo as src por uma imagem padrão de erro.
     */
//    $('img').error(function () {
//        $(this).attr("src", "img/erro.png");
//    });
});