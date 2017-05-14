/* CT376: Project #4, Event Handling, callbacks and functions using JQuery
Name: Ebony Cross
Professor: Franceschi
Due Date: 4//16
*/

$(start);


/*when the user clickes, on the text field, first, a calendar shows up and executes an alert using a callback function*/
function start( )
{
alert("Inside Calendar function...");
  $( "#cal" ).datepicker(  { altField: "#alternate", altFormat: "DD, d MM, yy", changeYear: true, changeMonth: true, duration: 2000, onSelect: calFeedback} );



/*change the class of the h1 hearders whoes class is the one in the html created and change color to green*/
    var headers = $("h1");
    alert("removing h1 class");
    headers.removeClass("header");
    alert("Adding class newH1 to all H1 headers");
     alert("now changing the h1 header's background to green");
    headers.addClass("newH1");


/*change the width of all the paragraphs, slide up and slide down only the 2nd paragraph (slide down in 2s and call afunction after this finishes).
Also make the 2nd paragraph draggable and resizable*/
    alert("Inside function to modify paragraphs...");

    var pars = $("p");
    pars.animate({"left":500, "width": 200}, 7000, call);
        alert("changing the WIDTH of paragraph 1 and 2");

    alert("Preparing to slide the 2nd paragraph up");
    $("#par2").slideUp(3000);

      alert("Then slide the 2nd paragraph down");
    $("#par2").slideDown(2000,"linear", call);

     //make the 2nd paragraph draggable
        alert("making the 2nd paragraph draggable");
       $("p:eq(1)").draggable({cursor: "crosshair", stop:dragFeedback });

    //make the 2nd paragraph resizable
    alert("making the 2nd paragraph resizable");
    $("p:eq(1)").resizable({handles: "se, ne, e", stop:resizeFeedback});
}


//call back function for calendar widget
function calFeedback(){
    var text = $("#cal").val();
    alert("Inside function callback for calendar. "+ "Date Selected: " + text);
}



//default callback function
function call(){
    alert("inside callback function");
}


//call back function for resizables
function resizeFeedback(event, window)
{
alert("Width: " + window.size.width + " Height: " + window.size.height);
}


//call back function for draggable
function dragFeedback( event, ui )
{
  alert( "left: " + ui.offset.left + " top: " + ui.offset.top );

}








