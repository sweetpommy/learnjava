function fetchWithTimeout(url, options, timeout = 7000) {
    return Promise.race([
        fetch(url, options),
        new Promise((_, reject) => {
            setTimeout(() => reject(new Error('time')), timeout);
        }),
    ]);
}
/**
 *
 * @param {string} url
 * @param {object} request
 * @returns
 */
async function fetchApi(url, request) {
    let init = {
        cache: 'no-cache',
        credentials: 'include',
        method: request.method,
        mode: 'cors',
    };

    if (request.method.toUpperCase() !== 'GET') {
        init.body = JSON.stringify(request.data);
        init.headers= {
            'content-type': 'application/json',
        };
    }

    return await fetchWithTimeout(url, init, 30000)
        .then(function (response) {
            return response.text();
        })
        .catch((err) => {
            return {
                err: err,
                message: '連線發生異常，請稍後再試，謝謝！',
                title: '連線錯誤',
            };
        });
}

var ezFavorites = new function () {	

	init();

	function setEndpoint(type){
		var endpointURL = {};
		if (location.protocol.indexOf("https") > -1) {
			endpointURL = {
				ENV: "prod",
				userauth: "https://member.eztravel.com.tw/",
				GCP: "https://static.cdn-eztravel.com.tw/"
			};
		} else {
			endpointURL = {
				ENV: "test",
				userauth: "http://mem00t-w01.eztravel.com.tw/",
				GCP: "https://static-test.cdn-eztravel.com.tw/"
			};
		}
		if (type === "new") window.ApiEndpointConfig = {};
		ApiEndpointConfig.COMMON_ENV_ENDPOINT = endpointURL.ENV;
		ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT = endpointURL.userauth;
		ApiEndpointConfig.COMMON_STATIC_CDN_ENDPOINT = endpointURL.GCP;
	}

	function init() {
		if (typeof(ApiEndpointConfig) === "object") {
			if (
				!ApiEndpointConfig.hasOwnProperty("COMMON_ENV_ENDPOINT") && 
				!ApiEndpointConfig.hasOwnProperty("COMMON_USERAUTH_ENDPOINT") && 
				!ApiEndpointConfig.hasOwnProperty("COMMON_STATIC_CDN_ENDPOINT")
			) setEndpoint();
		} else setEndpoint("new");
		window.FAVORITES_SOURCE_REFERENCE = ApiEndpointConfig.COMMON_ENV_ENDPOINT !== "dev" ? ApiEndpointConfig.COMMON_STATIC_CDN_ENDPOINT : location.protocol + "//" + location.host + "/";
		window.wishListCheck = false; //檢查 modal 是否被開起來(true 代表開啟)
		cachedScript(FAVORITES_SOURCE_REFERENCE + 'assets/jslib/rsa.js'); //載入相關資源
	}
	
	function cachedScript(url) {
		return new Promise(function(resolve, reject) {
			var script = document.createElement("script");
			script.onload = resolve;
			script.onerror = reject;
			script.src = url ;
			document.getElementsByTagName("head")[0].appendChild(script);
		});
	}

	function showWishFinishModal(data) { //顯示完成的modal
		var loadEzModal = FAVORITES_SOURCE_REFERENCE + 'assets/jslib/ezModal_bs5.js';
		var loadEzProdTrack = FAVORITES_SOURCE_REFERENCE + 'assets/jslib/ezProdtrack.js';

		cachedScript(loadEzModal)
		.then(function(){
			return cachedScript(loadEzProdTrack)	
		}) 
		.then(function(){
			if (window._headerNextStep) { //發現有_headerNextStep
				var onHidden = function () {
					getLoginUserData(function (data) {
						window._headerNextStep(data);
					});
				};
			}
			var wishList = new EzModal({
				content: get_ezProdtrack_html(data),
				height: "310px",
				onHidden: onHidden || function () { }
			});
			setTimeout(function () {
				window.wishListCheck = false; //modal設為關閉 
				wishList.show();
			}, 5);
		}).catch(function(err){
			console.log(err);
		});
	}

    function showEzLoginModal(fn, para, action){
		ezLogin.show({
			onComplete: function () {
				fn(para, action);
			},
            favoriteDesc: true,
            disB2vLogin: true
		});
	}
	
	function addProdtrack(options){
		var generateUrl = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT + 'auth/crypto/generate';
		fetchApi(generateUrl,{method:'GET'})
		.then(function(data){
			data = JSON.parse(data);
			var hash = data["hash"];
			var pub = data["pubKey"];
			var rsa = new RSAKey();
			rsa.setPublic(pub, "10001"); //10001 = public exponent是固定
			var encProdSubtype = rsa.encrypt(options.prodSubtype);
			// 收藏商品
			var wishListUrl = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT +
				'auth/wishList?' +
				'encProdSubtype=' + encProdSubtype +
				'&hash=' + hash +
				'&wishInfo=' + encodeURIComponent(JSON.stringify(options));
			return fetchApi(wishListUrl,{method:'GET'})
		})
		.then(function(data){
			var info = JSON.parse(data);
			if(info.status === 200){
				// 舊版(之後需移除)
				if(document.getElementById("ezFavorBtn")) document.getElementById("ezFavorBtn").setAttribute("favorstatus",true);
				// 新版
				var checkItems = document.querySelector('[data-seqno="'+ options.seqNo +'"]'); 
				if(checkItems){
					 checkItems.classList.add("ezFavSuccess");
					 checkItems.setAttribute("data-favorstatus",true);
				}
			}
			showWishFinishModal(info);
		});
	}

	function removeProdtrack(webUrl,seqNo){
		var deleteUrl = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT + 'auth/wishList/delete?webUrl=' + webUrl ; 
		fetchApi(deleteUrl,{method:'GET'})
		.then(function(data) {
			var info = JSON.parse(data);
			if(info.statusCode === 200){
				var checkItems = document.querySelector('[data-seqno="'+ seqNo +'"]'); 
				if(checkItems){
					 checkItems.classList.remove("ezFavSuccess");
					 checkItems.setAttribute("data-favorstatus",false);
				}
			}
		});
	}

	function checkProdTrack(options){
		var collectUrl = [];
		options.prod.forEach((value)=>{
			collectUrl.push(value.webUrl);
		});
		var dataJSON = {
			"prod": collectUrl
		};
		var checkUrl =  ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT + 'auth/wishList/query';
		fetchApi(checkUrl, {method: 'POST',data: dataJSON })
		.then(function(data){
			var info = JSON.parse(data);

			// if(info.status === 200){
				options.prod.forEach((value)=>{
					var status =  info[value.webUrl] || false;
					var checkItems = document.querySelector('[data-seqno="'+ value.seqNo +'"]'); 
					if(checkItems){
						console.log("---check---");
						console.log(checkItems);
						console.log("status:",status);
						// checkItems.setAttribute("data-favorstatus", status ) 
						status ? checkItems.classList.add("ezFavSuccess") : checkItems.classList.remove("ezFavSuccess");
						checkItems.setAttribute("data-favorstatus",status);
					}
				});
			// }
		});
	}

	function errorHandler(data) {
		showWishFinishModal(data);
	}

	this.favorites = function(options, action){
		try {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			       var result = xhttp.responseText;
			       if('true' === result){
			       	 document.domain = 'eztravel.com.tw';
			       	 console.info("開啟網域宣告");
			       } else {
			       	 console.info("關閉網域宣告");
			       }
			    }
			};
			xhttp.open("GET", ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT+'auth/setting/member?key=DOCUMENT_DOMAIN&' + new Date().getTime(), true);
			xhttp.send();
		} catch (e) {
		  console.error("Hi there, We detect your web site look like not our eztravel.com.tw domain. It may be error, thanks");
		}

		if (options) {
			if (window.wishListCheck === true) return;//代表modal已經開啟，離開
			window.wishListCheck = true; //將modal 設為開啟
			var loginRoleCheckUrl = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT + 'auth/loginRoleCheck';
			fetchApi(loginRoleCheckUrl,{method:'GET'})
            .then(function(data){
				action = action || '01'; // 預設打開
                if ('B2C' === data || 'B2E' === data) {
					switch (action) {
						case '01': 	// 新增收藏
							addProdtrack(options);
							break;
						case '02':  // 刪除收藏
							removeProdtrack(options.webUrl, options.seqNo);
							window.wishListCheck = false; // 沒有用到 modal 把它關起來
							break;
						case '03':  // 查詢收藏
							checkProdTrack(options);
							window.wishListCheck = false;  // 沒有用到 modal 把它關起來
							break;
					}
                } else if ('WEBORDER' === data || 'B2B' === data || 'B2F' === data) {
                    // 不做任何事
                    window.wishListCheck = false;
                } else {
                    // 尚未登入
                    window.wishListCheck = false;
                    if (window.headerNextStep) { //如果發現本頁有實作 headerNextStep
                        window._headerNextStep = window.headerNextStep;//把headerNextStep暫時傳給_headerNextStep
                        window.headerNextStep = function () { }; //清空
                    }
                    cachedScript(FAVORITES_SOURCE_REFERENCE + 'assets/jslib/ezLogin.js')
                    .then(function(){
                        setTimeout(function () {
                            var detectModal = document.querySelector(".G-ezModal.fade");
                            if(!!detectModal) detectModal.parentNode.removeChild(detectModal);  // 將先前產生的 ezLogin modal一併移除
                            if(action === '03') return 
							showEzLoginModal(ezFavorites.favorites, options, action);
                        }, 5);//非同步延遲 避免onComplete fn被覆蓋導致動作失效
                    });
                }
            })
            .catch(function(){
                errorHandler({ 'status': 500, 'desc': 'question!連線發生異常，請稍後再試，謝謝！(0x3)' });	
            });
		}
	};

	if(typeof jQuery === "function"){ // jQuery
		$.extend({
			favorites: function(options, action){
				ezFavorites.favorites(options, action);
			}
		});
	}else { // Zepto
		$.extend($.fn, {
			favorites: function(options, action){
				ezFavorites.favorites(options, action);
			}
		});
	}
}();
