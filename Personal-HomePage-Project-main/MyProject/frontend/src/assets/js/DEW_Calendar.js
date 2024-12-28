/* 달력에서 날짜 클릭 */

// 날짜를 클릭했을 때 실행되는 함수
function viewDiary(day) {
    const today = new Date(); // 현재 날짜 가져오기
    const year = today.getFullYear();
    const month = today.getMonth() + 1; // 월은 0부터 시작하므로 1을 더함

    alert(`${year}년 ${month}월 ${day}일의 일기를 조회합니다.`);

    // 여기에서 서버로 해당 날짜 데이터를 요청하거나, 다른 로직을 추가
    // 예: API 요청
    // fetch(`/api/diary?year=${year}&month=${month}&day=${day}`)
    //     .then(response => response.json())
    //     .then(data => console.log(data));
}

/* 일기에서 날짜 자동 조회 */

// 오늘 날짜를 자동으로 표시
const todayElement = document.getElementById("today-date");
const today = new Date();

// 날짜 포맷 지정
const year = today.getFullYear();
const month = String(today.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 +1
const date = String(today.getDate()).padStart(2, "0");
const weekDays = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
const day = weekDays[today.getDay()];

// 날짜 문자열 생성
const formattedDate = `${year}년 ${month}월 ${date}일 (${day})`;

// HTML에 날짜 삽입
todayElement.textContent = formattedDate;
