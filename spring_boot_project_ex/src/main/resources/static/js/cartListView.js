/**
 * cartListView.js
 */
 
  $(document).ready(function(){
  	// [전체 선택] 체크박스 체크했을 때
  	$('#allCheck').on('click', function(){
  		// [전체 선택] 체크박스 상태 확인
  		var chk = $('#allCheck').prop("checked");
  		
  		if(chk) {  // true : 체크되었다면
  			$('.chkDelete').prop("checked", true);
  		} else {
  			$('.chkDelete').prop("checked", false);
  		}  		
  	});
  	
  	// 개별 체크박스 (.chkDelete) 하나라도 해제할 경우 [전체선택] 체크박스 해제
  	// 모두 체크되었을 때 [전체선택] 체크
  	/*
  	$('.chkDelete').on("click", function(){
  		var count = 0;
  		$('.chkDelete').each(function(){
  			if($(this).is(':checked'))
  				count++;
  		});  	
  		
  		if(count == $('.chkDelete').length)  // 체크된 수가 전체 개수라면 
  			$('#allCheck').prop("checked", true);
  		else
  			$('#allCheck').prop("checked", false);  	
  	});
  	*/
  	
  	$(".chkDelete").click(function(){
		var total = $(".chkDelete").length;   // 전체 체크박수 개수
		var checked = $(".chkDelete:checked").length; // 체크된 체크박스 개수

		if(total != checked)   // 두 수가 같다면 (모두 선택된 상태가 아니라면)
			$("#allCheck").prop("checked", false);  // 해제
		else // 모두 선택했으면 
			$("#allCheck").prop("checked", true);  // 체크
	});
	
	// 장바구니 목록에서 선택한 상품 삭제
	// [삭제] 버튼 클릭 시 : deleteCartBtn
	
	$("#deleteCartBtn").click(function(){
	
		//(1) 선택 여부 확인 : 한 개라도 선택하면 true, 아무것도 선택하지 않으면 false
		var checked = $('.chkDelete').is(':checked');
		
		// (2) 선택 여부 결과에 따라 if 문 작성
		// 선택하지 않은 경우 "선택된 상품이 없습니다" 출력
		if(checked){       			//if($('.chkDelete:checked').length == 0)		
			// 삭제 확인 여부
			var answer = confirm("선택된 상품을 삭제하시겠습니까?");
			
			if(answer) {
				// 체크된 체크박스의 cartNo를 배열에 추가
				var checkArr = new Array();
				$('.chkDelete:checked').each(function(){
					checkArr.push($(this).attr("data-cartNo")); 			
				});						
				
				// 전송하고 결과 받고, 포워딩
				// deleteCart로 요청
				// 결과가 1이면 cartList로 포워딩			
				$.ajax({
					type:"post",
		 			url:"deleteCart",  //    또는  "/product/deleteCart"
		 			data:{"chkArr": checkArr},
		 			success:function(result){
		 				// 성공 시 수행되는 함수 
		 				// 반환되는 값을  result로 받음
		 				if(result == 1) {
		 					location.href = "/product/cartList"; 		 					
		 				}
		 			},
		 			error:function(){
		 				// 오류있을 경우 수행되는 함수
		 				alert("전송 실패");
		 			}
				});			
			}			
		} else{
			alert("선택된 상품이 없습니다.");			
		}
	
	});
	
  });
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  