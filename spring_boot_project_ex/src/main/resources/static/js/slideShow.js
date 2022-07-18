/**
 * slideShow.js
 * next 버튼 클릭하면 뒤로 이동
 * prev 버튼 클릭하면 앞으로 이동 
 */
 
$(document).ready(function() {
	// 이동한 이미지의 index 값 저장(현재 위치)
	var movedIndex = 0;
	
	// 슬라이드 패널을 움직이는 함수
	function moveSlide(index) {
		// 전달 받은 index 값을 movedIndex에 저장
		movedIndex = index;
		
		// 슬라이드 이동
		var moveLeft = -(index * 1280);		// 왼쪽으로 이동 거리
		$('#slidePanel').animate({'left':moveLeft}, 'slow');
	}
	
	// prev 버튼 클릭하면 앞으로 이동
	$('#prevButton').on('click', function() {
		// 현재 Index 위치 계산
		if(movedIndex != 0)	// 첫 번째가 아니면
			movedIndex = movedIndex - 1;	// 왼쪽으로 한 칸 이동
			
		moveSlide(movedIndex);	// 실제 이동하는 함수 호출 (인덱스값 전달)	
	});
	
	// next 버튼 클릭하면 뒤로 이동
	$('#nextButton').on('click', function() {
		// 현재 Index 위치 계산
		if(movedIndex != 4)	// 마지막이 아니면
			movedIndex = movedIndex + 1;	// 오른쪽으로 한 칸 이동
			
		moveSlide(movedIndex);	// 실제 이동하는 함수 호출 (인덱스값 전달)	
	});
	
	// 초기에 슬라이더 위치 랜덤하게 지정
	var randomNumber = Math.floor(Math.random() * 5);
	moveSlide(randomNumber);
	
	// 각 컨트롤 버튼에 대해 처리
	$('.controlButton').each(function(index) {
		$(this).hover(
			function() {
				$(this).attr('src', 'image/controlButton2.png');
			},
			function() {
				$(this).attr('src', 'image/controlButton1.png');
			}
		);
		
		// 클릭했을 때 인덱스값을 moveSlide() 함수에게 전달
		$(this).on('click', function() {
			moveSlide(index);	
		});
	});
});