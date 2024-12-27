let subToggle=true;
$(".menu").click(()=>{
    if(subToggle){
        $(".sub").slideDown(300);
    }else{
        $(".sub").slideUp(600);
    }
    subToggle=!subToggle;
});

