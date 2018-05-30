<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<link rel="stylesheet" href="resources/assets/css/fullcalendar.css" />
<link rel="stylesheet" href="resources/assets/css/ace.min.css" />
<link rel="stylesheet" href="resources/assets/css/ace-responsive.min.css" />
<link rel="stylesheet" href="resources/assets/css/ace-skins.min.css" />
<div class="page-content">
  <div class="page-header position-relative">
    <!--div class="form-group">
      <a href="/ae_controlTunel" class="btn btn-primary btn-small">
        <i class="icon-exchange  bigger-125"></i>
        Regresar
      </a>
    </div-->
    <h1>
      Apartado Tunel
      <small>
        <i class="icon-double-angle-right"></i>
        Calendario
      </small>
    </h1>
  </div><!--/.page-header-->

  <div class="row-fluid">
    <div class="span12">
      <!--PAGE CONTENT BEGINS-->

      <div class="row-fluid">
        <div class="span9">
          <div class="space"></div>

          <div id="calendar"></div>
        </div>

        <!--div class="span3">
          <div class="widget-box transparent">
            <div class="widget-header">
              <h4>Draggable events</h4>
            </div>

            <div class="widget-main">
              <div id="external-events">
                <div class="external-event label-grey" data-class="label-grey">
                  <i class="icon-move"></i>
                  My Event 1
                </div>

                <div class="external-event label-success" data-class="label-success">
                  <i class="icon-move"></i>
                  My Event 2
                </div>

                <div class="external-event label-important" data-class="label-important">
                  <i class="icon-move"></i>
                  My Event 3
                </div>

                <div class="external-event label-purple" data-class="label-purple">
                  <i class="icon-move"></i>
                  My Event 4
                </div>

                <div class="external-event label-yellow" data-class="label-yellow">
                  <i class="icon-move"></i>
                  My Event 5
                </div>

                <div class="external-event label-pink" data-class="label-pink">
                  <i class="icon-move"></i>
                  My Event 6
                </div>

                <div class="external-event label-info" data-class="label-info">
                  <i class="icon-move"></i>
                  My Event 7
                </div>

                <label>
                  <input type="checkbox" class="ace-checkbox" id="drop-remove" />
                  <span class="lbl"> Remove after drop</span>
                </label>
              </div>
            </div>
          </div>
        </div-->
      </div>

      <!--PAGE CONTENT ENDS-->
    </div><!--/.span-->
  </div><!--/.row-fluid-->
</div><!--/.page-content-->
<script type="text/javascript">
  $(function() {

    /* initialize the external events
     -----------------------------------------------------------------*/

    $('#external-events div.external-event').each(function() {

      // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
      // it doesn't need to have a start or end
      var eventObject = {
        title: $.trim($(this).text()) // use the element's text as the event title
      };

      // store the Event Object in the DOM element so we can get to it later
      $(this).data('eventObject', eventObject);

      // make the event draggable using jQuery UI
      $(this).draggable({
        zIndex: 999,
        revert: true,      // will cause the event to go back to its
        revertDuration: 0  //  original position after the drag
      });

    });




    /* initialize the calendar
     -----------------------------------------------------------------*/

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();


    var calendar = $('#calendar').fullCalendar({
      buttonText: {
        prev: '<i class="icon-chevron-left"></i>',
        next: '<i class="icon-chevron-right"></i>'
      },

      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      events: [
        <c:forEach items="${listaTunel}" var="tunel">
        <c:if test="${(tunel.periodo eq '12') || (tunel.periodo eq '24')}">
        {
          title: '${tunel.nombreCliente}',
          start: new Date(${tunel.fechaCapturada.substring(6,10)}, ${tunel.fechaCapturada.substring(3,5)}-1, ${tunel.fechaCapturada.substring(0,2)}),
          folio: '${tunel.folioAsignado}'
        },
        </c:if>
        <c:if test="${tunel.periodo eq '48'}">
        {
          title: '${tunel.nombreCliente}',
          start: new Date(${tunel.fechaCapturada.substring(6,10)}, ${tunel.fechaCapturada.substring(3,5)}-1, ${tunel.fechaCapturada.substring(0,2)}),
          end: new Date(${tunel.fechaCapturada.substring(6,10)}, ${tunel.fechaCapturada.substring(3,5)}-1, ${tunel.fechaCapturada.substring(0,2)}+1),
          folio: '${tunel.folioAsignado}'
        },
        </c:if>
        </c:forEach>
        {
          title: 'Some Event',
          start: new Date(2001, 3, 5),
          allDay: false
        }
        ]
      ,
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar !!!
      drop: function(date, allDay) { // this function is called when something is dropped

        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');
        var $extraEventClass = $(this).attr('data-class');


        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }

      }
      ,
      selectable: true,
      selectHelper: true,
      select: function(start, end, allDay) {
        var form = $("<p class='form-inline'><label>Apartado Tunel Detalle &nbsp;</label></p>");
        $.ajax("controlTunel", {
          "type": "POST",
          "beforeSend": function() {
            var loader = $('<div align="center"><img src="resources/img/loader.GIF"/></div>')
            var alto=$(window).height();
            bootbox.dialog(loader).css({"width": "220px","left":"875px","margin-top":(alto/2)-30+"px"});
          },
          "success": function(result){
            bootbox.hideAll();
            $("#CapSeg").css("font-size","12px");
            bootbox.dialog(form.html(result), [{
                      "label" : "Cerrar",
                      "class" : "btn-small btn-primary",
                      "callback": function() {
                        form.html("");
                      }
                    }]
            ).css({"width":"1000px","heith":"660","left":"450px","overflow-y": "hidden"});

            return false;
          },
          "error": function(result) {
            bootbox.alert("Error al recuperar la información", result);
            $("#CapSeg").html("");
          },
          "data": {fecha:start},
          "async": true
        });


        calendar.fullCalendar('unselect');

      }
      ,
      eventClick: function(calEvent, jsEvent, view) {

        var form = $("<p class='form-inline'><label>Apartado Tunel Detalle &nbsp;</label></p>");
        var folioAsignado=calEvent.folio;
        $.ajax("listaTunel", {
          "type": "POST",
          "beforeSend": function() {
            $("#CapSeg").css("font-size","27px");
            $("#CapSeg").html('Cargando ...');
          },
          "success": function(result){
            $("#CapSeg").css("font-size","12px");
            bootbox.dialog(form.html(result), [{
                      "label" : "Cerrar",
                      "class" : "btn-small btn-primary",
                      "callback": function() {
                        form.html("");
                      }
                    }]
            ).css({"width":"1000px","heith":"660","left":"450px","overflow-y": "hidden"});

            return false;
          },
          "error": function(result) {
            bootbox.alert("Error al recuperar la información", result);
            $("#CapSeg").html("");
          },
          "data": {folioAsignado:folioAsignado},
          "async": true
        });

        /*form.on('submit', function(){
          calEvent.title = form.find("input[type=text]").val();
          calendar.fullCalendar('updateEvent', calEvent);
          div.modal("hide");
          return false;
        });*/


        console.log(calEvent.id);
        console.log(jsEvent);
        console.log(view);

        // change the border color just for fun
        $(this).css('border-color', 'red');

      }

    });


  })
</script>