$(document).ready
(
    function () {

        $("[type='number']").keypress(function (evt) {
            evt.preventDefault();
        });


        $(document).on('change', "#groupEdit", function () {
            $("#groupEdit").val($(this).val());
        });


        $(document).on('change', "#placeEdit", function () {
            $("#placeEdit").val($(this).val());

        });


        $(document).on('click', '.editReceivement',
            function () {
                $("[name='idEdit']").val($(this).attr('id'));
                $("[name='startHourEdit']").val($(this).data('inizio'));
                $("[name='endHourEdit']").val($(this).data('fine'));
                $("[name='placeEdit']").val($(this).data('luogo'));


            });

        $(document).on('click', '#editButton', function () {

            if ($("#placeEdit").text().trim() === "") {
                alert("Professore, inserisca un luogo per il ricevimento");
                return
            }

            var start, end, luogo, numPosti, id;
            start = $("#startHourEdit").val();
            end = $("#endHourEdit").val();
            luogo = $("#placeEdit").val();
            id = $("[name='idEdit']").val();
            $.ajax(
                {
                    type: "POST",
                    url: "../General/receivement",
                    data:
                        {
                            id: id,
                            inizio: start,
                            fine: end,
                            luogo: luogo,
                            operazione: "modifica"
                        },
                    success: function (results) {
                        document.location = "../Professore/HomeProfessore.jsp";
                    }

                });

        });

        $(document).on('click', '#deleteButton', function () {

            var id;
            id = $("[name='idEdit']").val();
            $.ajax(
                {
                    type: "POST",
                    url: "../General/receivement",
                    data:
                        {
                            id: id,
                            operazione: "elimina"
                        },
                    success: function (results) {
                        document.location = "../Professore/HomeProfessore.jsp";
                    }

                });


        });

        $("#prenotazioniButton").click(function () {
            var idEdit = $("#idEdit").val();
            var tipo = "visualizza";

            $.post("/View/General/receivement", {
                "idEdit": idEdit,
                "operazione": tipo,
            }, function (data, status) {
                $(".tbodymodalbody").empty();
                var x = 0;
                $.each(data, function (i, item) {
                    x++;
                    $(".tbodymodalbody").append("<tr>" +
                        "                        <th scope=\"row\">" + x + "</th>" +
                        "                        <td>" + item.lista + "</td>" +
                        "                        <td>" + item.motivazione + "</td>" +
                        "                        <td><input type='radio' id='presenza"+i+"' name='presenza" + i + "'></td>" +
                        "                        <td><input type='radio' id='presenza"+i+"' name='presenza" + i + "'></td>" +
                        "                    </tr>"
                    );
                });
            });
            $(".modalbody").append("    </tbody>");
        });


        $(document).on('click','#presenza1',function(){
            alert("CIAO");
        });

    }
)