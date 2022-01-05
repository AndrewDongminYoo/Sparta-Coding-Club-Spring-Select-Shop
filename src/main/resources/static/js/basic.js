let targetId;

$(document).ready(function () {
    // id 가 query 인 녀석 위에서 엔터를 누르면 execSearch() 함수를 실행하라는 뜻입니다.
    $('#query').on('keypress', function (e) {
        if (e.key === 'Enter') {
            execSearch();
        }
    });
    $('#close').on('click', function () {
        $('#container').removeClass('active');
    })

    $('.nav div.nav-see').on('click', function () {
        $('div.nav-see').addClass('active');
        $('div.nav-search').removeClass('active');

        $('#see-area').show();
        $('#search-area').hide();
    })
    $('.nav div.nav-search').on('click', function () {
        $('div.nav-see').removeClass('active');
        $('div.nav-search').addClass('active');

        $('#see-area').hide();
        $('#search-area').show();
    })

    $('#see-area').show();
    $('#search-area').hide();

    if ($('#admin').length === 1) {
        showProduct(true);
    } else {
        showProduct();
    }
})

function showProduct(isAdmin = false) {
    let productContainer = $('#product-container')
    // 1. GET /api/products 요청
    // 2. #product-container(관심상품 목록), #search-result-box(검색결과 목록) 비우기
    // 3. for 문 마다 addProductItem 함수 실행시키고 HTML 만들어서 #product-container 에 붙이기
    $.ajax({
        type: 'GET',
        url: isAdmin ? '/api/admin/products' : '/api/products',
        success: function (response) {
            productContainer.empty();
            $('#search-result-box').empty();
            for (let i = 0; i < response.length; i++) {
                let product = response[i];
                let tempHtml = addProductItem(product);
                productContainer.append(tempHtml);
            }
        }
    })
}

function addProductItem(product) {
    const {link, lowPrice, image, title, myPrice} = product;
    return `<div class="product-card" onclick="window.location.href='${link}'">
                <div class="card-header">
                    <img src="${image}"
                         alt="">
                </div>
                <div class="card-body">
                    <div class="title">
                        ${title}
                    </div>
                    <div class="lowPrice">
                        <span>${numberWithCommas(lowPrice)}</span>원
                    </div>
                    <div class="isgood ${lowPrice > myPrice ? 'none' : ''}">
                        최저가
                    </div>
                </div>
            </div>`;
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function execSearch() {
    // 1. 검색창의 입력값을 가져온다.
    let searchInput = $('#query')
    let query = searchInput.val();
    // 2. 검색창 입력값을 검사하고, 입력하지 않았을 경우 focus.
    if (!query) {
        alert("검색어를 입력하세요.");
        searchInput.focus();
        return;
    }
    $.ajax({
        // 3. GET /api/search?query=${query} 요청
        type: "GET",
        url: `/api/search?query=${query}`,
        success: function (response) {
            let resultBox = $('#search-result-box')
            resultBox.empty();
            response && response.forEach((itemDto) => {
                // 4. for 문마다 itemDto 를 꺼내서 HTML 만들고 검색결과 목록에 붙이기!
                let tempHtml = addHTML(itemDto);
                resultBox.append(tempHtml);
                searchInput.val("");
            })
        }
    })
}

function addHTML(itemDto) {
    const { title, image, lowPrice } = itemDto;
    return `<div class="search-itemDto">
            <div class="search-itemDto-left">
                <img src="${image}" alt="item-image">
            </div>
            <div class="search-itemDto-center">
                <div>${title}</div>
                <div class="price">
                    ${numberWithCommas(lowPrice)}
                    <span class="unit">원</span>
                </div>
            </div>
            <div class="search-itemDto-right">
                <img src="../images/baseline_save_alt_black_24dp.png" alt="" onclick='addProduct(${JSON.stringify(itemDto)})'>
            </div>
        </div>`;
}

function addProduct(itemDto) {
    $.ajax({
        type: "POST",
        url: "/api/products",
        contentType: "application/json",
        data: JSON.stringify(itemDto),
        success: function (response) {
            $('#container').addClass('active');
            targetId = response.id;
        }
    })
}

function setMyPrice() {
    // 1. id가 myPrice 인 input 태그에서 값을 가져온다.
    let myPrice = $('#myPrice');
    // 2. 만약 값을 입력하지 않았으면 alert 를 띄우고 중단한다.
    if (!myPrice.val()) {
        alert("원하는 최저가를 입력해주세요. (닫기를 누르면 최저가 기능 해제)");
        myPrice.focus();
        return;
    }
    $.ajax({
        // 3. PUT /api/product/${targetId} 에 data 를 전달한다.
        type: "PUT",
        url: `/api/products/${targetId}`,
        contentType: "application/json",
        data: JSON.stringify({myPrice: myPrice.val()}),
        success: function () {
            // 4. 모달을 종료한다. $('#container').removeClass('active');
            $('#container').removeClass('active');
            // 5, 성공적으로 등록되었음을 알리는 alert 를 띄운다.
            alert("희망 가격 설정이 완료되었습니다 ._.");
            // 6. 창을 새로고침한다. window.location.reload();
            window.location.reload();
            targetId = "";
        }
    })
}