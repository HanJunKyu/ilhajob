var index = 1;

function edu_add(){
    var education = $('.education_form').clone();
    var new_edu = education.appendTo('.resume-edit-education');
    new_edu.attr('class','resume-wrapper education');
    new_edu.find("input,select").removeAttr("disabled");
    new_edu.show();
}

function career_add(){
    var career = $('.career_form').clone();
    var new_career = career.appendTo('.resume-edit-career');
    new_career.attr('class','resume-wrapper career');
    new_career.find("input,select").removeAttr("disabled");
    new_career.show();
}

function activity_add(){
    var activity = $('.activity_form').clone();
    var new_act = activity.appendTo('.resume-edit-activity');
    new_act.attr('class','resume-wrapper activity');
    new_act.find("input,select").removeAttr("disabled");
    new_act.show();
}

function lisense_add(){
    var lisense = $('.lisense_form').clone();
    var new_lisense = lisense.appendTo('.resume-edit-lisense');
    new_lisense.attr('class','resume-wrapper lisense');
    new_lisense.find("input,select").removeAttr("disabled");
    new_lisense.show();
}

function delete_resume(e){
    var parent = $(e).parent();
    parent.remove();
    
}

function showImage(event) { 
    var reader = new FileReader(); 

    reader.onload = function(event) { 
        var img = document.createElement("img"); 
        img.setAttribute("src", event.target.result); 
        img.setAttribute("width","148px");
        img.setAttribute("height","198px");
        img.setAttribute("id","prof_img")
        img.style.position = "absolute";
        img.style.zIndex = "10"
        document.querySelector("div.img-div").appendChild(img); 
        $('#remove-img').show();
        $('.btn-file').hide();
        $('#check').val("add"); 
        
    }; 
        reader.readAsDataURL(event.target.files[0]); 
    }

function removeImg(fileTagName){

    $('#prof_img').remove();
    $('#remove-img').hide();
    $('.btn-file').show();
    $('#check').val("remove"); 
    
	$("input[name='"+ fileTagName +"']").val("");


}

function addURL(){
    var url = $('#URL_form').clone();
    var new_url = url.appendTo('.resume-edit-portfolio');
    new_url.attr('id','URL');
    new_url.find("*").removeAttr("disabled");
    new_url.show();
}

function addFile(){
    var url = $('#file_form').clone();
    var new_url = url.appendTo('.resume-edit-portfolio');
    new_url.attr('id','file');
    new_url.find("*").removeAttr("disabled");
    new_url.show();
}

function showFile(e){

    var parent = $(e).parent();
    var input = parent.parent().children('input');
    var div = input.parent();
    var file = $(e).val();
    input.remove();
    div.prepend(input);
    input.attr('value',file)
    

}

var edu_check = false;

function addEducation(e) {

    var item = $('#edu_item');

    if($(e).attr('class') == "education-remove"){

        
        item.attr('class', 'selected');
        $(e).attr('class','education-remove remove');
        $(e).children().attr('class','fas fa-minus');
        $('.education').find("*").removeAttr("disabled");
        $('.resume-edit-education').show();
        $('#education-add').show();
        item.css('pointer-events','all');
        item.attr('href','#E')
		edu_check = true;
    }else {
        item.attr('class', '');
        $(e).attr('class','education-remove');
        $(e).children().attr('class','fas fa-plus');
        $('.education').find("*").attr("disabled", true);
        $('.resume-edit-education').hide();
        $('#education-add').hide();
        item.attr('href','')
        item.css('pointer-events','none');
        edu_check = false;
    }
}

var career_check = false;

function addCareer(e) {

    var item = $('#career_item');

    if($(e).attr('class') == "career-remove"){

        item.attr('class', 'selected');
        $(e).attr('class','career-remove remove');
        $(e).children().attr('class','fas fa-minus');
        $('.career').find("*").removeAttr("disabled");
        $('.resume-edit-career').show();
        $('#career-add').show();
        item.css('pointer-events','all');
        item.attr('href','#C')
        career_check = true;
    }else {
        $('#career_item').attr('class', '');
        $(e).attr('class','career-remove');
        $(e).children().attr('class','fas fa-plus');
        $('.career').find("*").attr("disabled",true);
        $('.resume-edit-career').hide();
        $('#career-add').hide();
        item.attr('href','')
        item.css('pointer-events','none');
        career_check = false;
    }
}

var activity_check = false;

function addActivity(e) {

    var item = $('#activity_item');

    if($(e).attr('class') == "activity-remove"){

        $('#activity_item').attr('class', 'selected');
        $(e).attr('class','activity-remove remove');
        $(e).children().attr('class','fas fa-minus');
        $('.activity').find("*").removeAttr("disabled");
        $('.resume-edit-activity').show();
        $('#activity-add').show();
        item.css('pointer-events','all');
        item.attr('href','#A')
        activity_check = true;
    }else {
        $('#activity_item').attr('class', '');
        $(e).attr('class','activity-remove');
        $(e).children().attr('class','fas fa-plus');
        $('.activity').find("*").attr("disabled", true);
        $('.resume-edit-activity').hide();
        $('#activity-add').hide();
        item.attr('href','')
        item.css('pointer-events','none');
        activity_check = false;
    }
}

var lisense_check = false;

function addLisense(e) {

    var item = $('#lisense_item');

    if($(e).attr('class') == "lisense-remove"){

        $('#lisense_item').attr('class', 'selected');
        $(e).attr('class','lisense-remove remove');
        $(e).children().attr('class','fas fa-minus');
        $('.lisense').find("*").removeAttr("disabled");
        $('.resume-edit-lisense').show();
        $('#lisense-add').show();
        item.css('pointer-events','all');
        item.attr('href','#L')
        lisense_check = true;
    }else {
        $('#lisense_item').attr('class', '');
        $(e).attr('class','lisense-remove');
        $(e).children().attr('class','fas fa-plus');
        $('.lisense').find("*").attr("disabled",true);
        $('.resume-edit-lisense').hide();
        $('#lisense-add').hide();
        item.attr('href','')
        item.css('pointer-events','none');
        lisense_check = false;
    }
}

var introduce_check = false;

function addIntroduce(e) {

    var item = $('#Introduce_item');

    if($(e).attr('class') == "introduce-remove"){

        $('#introduce_item').attr('class', 'selected');
        $(e).attr('class','introduce-remove remove');
        $(e).children().attr('class','fas fa-minus');
        $('.resume-edit-introduce').find("textarea").removeAttr("disabled");
        $('.resume-edit-introduce').show();
        item.css('pointer-events','all');
        item.attr('href','#I')
        introduce_check = true;
    }else {
        $('#introduce_item').attr('class', '');
        $(e).attr('class','introduce-remove');
        $(e).children().attr('class','fas fa-plus');
        $('.resume-edit-introduce').find("textarea").attr("disabled", true);
        $('.resume-edit-introduce').hide();
        item.attr('href','')
        item.css('pointer-events','none');
        introduce_check = false;
    }
}

var portfolio_check = false;

function addPortfolio(e) {

    var item = $('#portfolio_item');

    if($(e).attr('class') == "portfolio-remove"){

        $('#portfolio_item').attr('class', 'selected');
        $(e).attr('class','portfolio-remove remove');
        $(e).children().attr('class','fas fa-minus');
        $('.resume-edit-portfolio').show();
        item.css('pointer-events','all');
        item.attr('href','#P')
        portfolio_check = true;
    }else {
        $('#portfolio_item').attr('class', '');
        $(e).attr('class','portfolio-remove');
        $(e).children().attr('class','fas fa-plus');
        $('.resume-edit-portfolio').hide();
        item.attr('href','')
        item.css('pointer-events','none');
        portfolio_check = false;
    }
}

var military_check = false;

function addMilitary(e) {

    var item = $('#military_item');

    if($(e).attr('class') == "military-remove"){

        $('#military_item').attr('class', 'selected');
        $(e).attr('class','military-remove remove');
        $(e).children().attr('class','fas fa-minus');
        $('.resume-edit-military').find("*").removeAttr("disabled");
        $('.resume-edit-military').show();
        item.css('pointer-events','all');
        item.attr('href','#M')
        military_check = true;
    }else {
        $('#military_item').attr('class', '');
        $(e).attr('class','military-remove');
        $(e).children().attr('class','fas fa-plus');
        $('.resume-edit-military').find("*").attr("disabled", true);
        $('.resume-edit-military').hide();
        item.attr('href','')
        item.css('pointer-events','none');
        military_check = false;
    }
}

$('#introduce_textarea').keyup(function (e){
    var content = $(this).val();
    $('.text-count').html("총 글자수 "+"<span>"+content.length+"</span>자"); 

});

