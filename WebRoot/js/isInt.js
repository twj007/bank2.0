/**
 * 
 */
function isInteger(obj){   
           
	         reg=/^[0-9]*[1-9][0-9]*$/;   
	        if(!reg.test(obj)){   
	             alert("请输入正确的数字");
	             return  false;  
	         }else{   
	            return true; 
	         }   
	     }   