//相容永安M版用Zepto
var jslib = typeof(jQuery) === "function" ? jQuery : Zepto;
var ezLogin= new function() {
	var apiEnv={};
	var memberAuthLoad = false;//判斷資源是否載入
	var ezModalLoad = false; //判斷資源是否載入
	var headerName = ""; //上方的 modal header顯示的名稱
	var favoriteDesc = ""; // 控管 登入前收藏的敘述 樣式
	var claimDesc = "";
	var SOURCE_REF = ApiEndpointConfig.COMMON_ENV_ENDPOINT !== "dev" ? ApiEndpointConfig.COMMON_STATIC_CDN_ENDPOINT : location.protocol + "//" + location.host + "/";
	function setApiEnv(){
		var securityCheck = ApiEndpointConfig.COMMON_ENV_ENDPOINT === "prod" ? "" : "?security=" + (location.protocol === "https:" ? "true" : "false" ) ;
		var COMMON_USERAUTH_ENDPOINT = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT;
		var COMMON_MEMBER_ENDPOINT = ApiEndpointConfig.COMMON_ENV_ENDPOINT === "dev" && location.href.indexOf('member') ? "http://3w.eztravel.com.tw/member" : ApiEndpointConfig.COMMON_MEMBER_ENDPOINT;
		apiEnv = {
			"regFAQ" : COMMON_MEMBER_ENDPOINT +"/serviceFAQ?tab=reg",
			"loginFAQ" : COMMON_MEMBER_ENDPOINT +"/serviceFAQ?tab=login",
			"nonMemberFAQ" : COMMON_MEMBER_ENDPOINT +"/serviceFAQ?tab=nonMember",
			"statement": COMMON_MEMBER_ENDPOINT +"/statement",
			"privacy": COMMON_MEMBER_ENDPOINT +"/privacy",
			socialMedia: function(type, action){
				var path = '';
				var typeMap = ["fb","google","line","apple"];
				var typeName = [
										{
											regPc : "pcFbReg",
											regMweb : "mwebFbReg",
											loginHeader : "fbHeaderLogin",
											loginOrder : "fbOrderLogin",
											loginMweb : "mwebFbLogin"
										},
										{
											regPc : "pcGoogleReg",
											regMweb : "mwebGoogleReg",
											loginHeader : "googleHeaderLogin",
											loginOrder : "googleOrderLogin",
											loginMweb : "mwebGoogleLogin"
										},
										{
											regPc : "pcLineReg",
											regMweb : "mwebLineReg",
											loginHeader : "lineHeaderLogin",
											loginOrder : "lineOrderLogin",
											loginMweb : "mwebLineLogin"
										},
										{
											regPc : "pcAppleReg",
											regMweb : "mwebAppleReg",
											loginHeader : "appleHeaderLogin",
											loginOrder : "appleOrderLogin",
											loginMweb : "mwebAppleLogin"
										}			 
								] ;
						
				var mapIndex = typeMap.indexOf(type) ;
				if (action === "reg") {
					switch (ezModal_layoutEnv()) {
						case "PC":// 所有PC的社群註冊
							path = typeName[mapIndex].regPc ; 
							break;
						
						case "MWEB":// 所有MWEB的社群註冊
						default:
							path = typeName[mapIndex].regMweb ; 
					}
				} else if (action === "login") {
					switch (ezLogin.origin) {
						case "HEADER":// PC + MWEB HEADER的社群登入
							path = typeName[mapIndex].loginHeader ; 
							break;
						
						case "ORDER":// PC + MWEB ORDER的社群登入
							path = typeName[mapIndex].loginOrder ; 
							break;
						
						case "MWEB_LOGIN":// MWEB 登入頁的社群登入
						default:
							path =  typeName[mapIndex].loginMweb 
					}
				}
				addSocialLoginListener();
				return COMMON_USERAUTH_ENDPOINT + "auth/" + path + securityCheck;
			}
		};
	}
	/*-------------------------  HTML  -------------------------*/
	function oauthBlock(email){
		 return '<div class="ezModal-input-group">\
			<div class="ezModal-input-box ezModal-input-readonly withAddon">\
				<span class="ezModal-input-addon ezModal-input-email"></span>\
				<input type="email" autocomplete="off" maxlength="50" value="'+email+'" readonly>\
			</div>\
		</div>'
	}  
	function accountBlock(option){		//input 欄位資訊
		var info = {
			icon: {
				user: "user",
				// phone: "phone",
				email: "email"
			},
			placeholder: {
				login: "e-mail / 台灣手機號碼 / 身分證字號",
				email: "e-mail",
				emailPhone: "e-mail / 台灣手機號碼" 
			},
			type: {
				text: "text",
				tel: "tel"
			}
		};
		switch (option){
			/*================ login ================== */
			case "normalLogin":
				info = {
					icon: info.icon.user,
					id_name: "login-personId",
					placeholder: info.placeholder.login,
					maxlength: "50",
					type: info.type.text,
				}
				break;
			/*================ reg ================== */
			case "normalReg":
				info = {
					icon: info.icon.email,
					id_name: "reg-personId",
					placeholder: info.placeholder.emailPhone,
					maxlength: "50",
					type: info.type.text,
				}
				break;
			/*================ reset password ================== */
			case "normalResetPsw":
				info = {
					icon: info.icon.user,
					id_name: "resetPsw-personId",
					placeholder: info.placeholder.login,
					maxlength: "50",
					type: info.type.text,
				}
				break;
			/*================ non member ================== */
			case "nonMember":
				info = {
					icon: info.icon.email,
					id_name: "nonMember",
					placeholder: info.placeholder.email,
					maxlength: "50",
					type: info.type.text,
				}
				break;
		}
		return '<div class="ezModal-input-group">\
			<div class="ezModal-input-box withAddon">\
				<span class="ezModal-input-' + info.icon + ' ezModal-input-addon"></span>\
				<input id="ezModal-main-' + info.id_name + '" name="ezModal-main-' + info.id_name + '" autocomplete="new-password" class="ezModal-use_focus ezModal-detect-full email-autocomplete" placeholder="' + info.placeholder + '" maxlength=' + info.maxlength + ' value="" type=' + info.type + '>\
			</div>\
		</div>'
	}
	function passwordBlock(maxlength){
		return '<div class="ezModal-input-group">\
							<div class="ezModal-input-box withAddon">\
								<span class="ezModal-input-addon ezModal-input-lock"></span>\
								<input id="ezModal-login-password" name="ezModal-login-password" type="password" autocomplete="new-password" class="ezModal-use_focus" placeholder="密碼" maxlength="'+maxlength+'">\
								<div id="ezModal-pass-eye-login" class="ezModal-pass-eye"></div>\
							</div>\
						</div>'
	}
	function passwordRegBlock(){
		return '<div class="ezModal-input-group">\
							<div class="ezModal-input-box withAddon">\
								<span class="ezModal-input-addon ezModal-input-lock"></span>\
								<input id="ezModal-reg-password" name="ezModal-reg-password" type="password" autocomplete="new-password" class="ezModal-use_focus" placeholder="密碼" maxlength="13">\
								<div id="ezModal-pass-eye-reg" class="ezModal-pass-eye"></div>\
							</div>\
						</div>'
	}
	function setPasswordBlock(){
		return '<div class="ezModal-input-group">\
							 <div class="ezModal-input-box withAddon">\
								 <span class="ezModal-input-addon ezModal-input-lock"></span>\
								 <input id="ezModal-reset-password1" name="ezModal-reset-password" type="password" autocomplete="new-password" class="ezModal-use_focus" placeholder="輸入新密碼第一次" maxlength="13">\
							 </div>\
						</div>\
						<div class="ezModal-input-group">\
							 <div class="ezModal-input-box withAddon">\
								 <span class="ezModal-input-addon ezModal-input-lock"></span>\
								 <input id="ezModal-reset-password2" name="ezModal-reg-password" type="password" autocomplete="new-password" class="ezModal-use_focus" placeholder="輸入新密碼第二次" maxlength="13">\
							 </div>\
						</div>'
	}
	function captchaImgBlock(){
		return '<div class="ezModal-captcha-wrap ezModal-input-group">\
			<div class="ezModal-captcha-group clearfix">\
				<div class="ezModal-semi-left">\
					<div class="ezModal-input-box">\
						<input type="text" autocomplete="off" id="ezModal-captcha-response" class="ezModal-use_focus ezModal-detect-full" maxlength="4" placeholder="請輸入右圖文字">\
					</div>\
				</div>\
				<div class="ezModal-captchaImg-wrap ezModal-semi-right">\
					<img id="ezModal-captchaImg">\
					<div id="ezModal-captcha-refresh" class="ezModal-captcha-refreshBtn"></div>\
 					<input type="hidden" id="ezModal-captchakey"/>\
				</div>\
			</div>\
		</div>\
		<div id="ezModal-main-error" class="ezModal-error-msg noClear"></div>'
	}
	function captchaImgUnfocusBlock(){ 
		return '<div class="ezModal-captcha-wrap ezModal-input-group">\
			<div class="ezModal-captcha-group clearfix">\
				<div class="ezModal-semi-left">\
					<div class="ezModal-input-box">\
						<input type="text" autocomplete="off" id="ezModal-captcha-response-unfocus" class="ezModal-use_focus ezModal-detect-full" maxlength="4" placeholder="請輸入右圖文字">\
					</div>\
				</div>\
				<div class="ezModal-captchaImg-wrap ezModal-semi-right">\
					<img id="ezModal-captchaImg-unfocus">\
					<div id="ezModal-captcha-refresh-unfocus" class="ezModal-captcha-refreshBtn"></div>\
 					<input type="hidden" id="ezModal-captchakey-unfocus"/>\
				</div>\
			</div>\
		</div>'
	}
	function authCodeFormBlock(num, options){
		function inputAmount(_num){
			var input = '<input id="ezModal-authCode-input" type="text" maxLength="'+ _num +'" inputmode="numeric" placeholder="請輸入驗證碼"></input>';
			return input;
		}

		return formWrapBlock('\
						<div class="ezModal-authCode">\
							<div>請於 10 分鐘內輸入驗證碼</div>\
							<div class="ezModal-authCode-input">\
								'+ inputAmount(num) +'\
							</div>\
							<input type="hidden" id="ezModal-completeAuthCode">\
							<div id="ezModal-authCode-error" class="ezModal-error-msg noClear"></div>\
						</div>\
						<div class="ezModal-btn-center">\
							<button id="ezModal-'+ options +'-authCode-button" class="ezModal-greenBtn ezModal-authCodeBtn disabled" disabled="true">送出</button>\
						</div>\
						<div class="ezModal-btn-center">\
							<button class="ezModal-resendAuthCodeBtn" data-action='+ options +'>重送驗證碼</button>\
						</div>');
	}
	function loginStatusBlock(){
		return '<div class="ezModal-main-checkbox-group">\
							<input id="ezModal-main-autoLogin" type="checkbox" class="ezModal-main-autoLogin" checked >\
							<label for="ezModal-main-autoLogin" class="ezModal-main-checkboxLabel">保持登入狀態</label>\
							<span class="ezModal-greenLink pull-right" style="margin-top: 4px;" onclick="ezLogin.moveResetPswModal1()">忘記密碼</span>\
						</div>'
	}
	function resetPasswordLinkBlock(){
		return '<div class="pull-right ezModal-linkWrap" style="height: 20px; margin-top: 8px;">\
							<span class="ezModal-greenLink" onclick="ezLogin.moveResetPswModal1()">忘記密碼</span>\
						</div>';
	}
	function largeButtonBlock(id,text){
	  return '<div>\
							<button id='+ id +' class="ezModal-greenBtn">' + text + '</button>\
						</div>'
	}
	function mainBlock(content){
		return '<div class="ezModal-main-wrap">\
							<div class="ezModal-main-left"></div>\
							<div class="ezModal-main-right">\
								'+content+'\
							</div>\
						</div>'
	}
	function formWrapBlock(content){
		return '<form autocomplete="off">\
		'+content+'\
		</form>'
	}
	function oauthModalBlock(email,tokenId){
		return '\
				<div class="ezModal-wrap" id="ezModal-oauth">\
					<div class="ezModal-h1">會員登入</div>\
					<div class="ezModal-h2">e-mail 信箱已存在，請輸入原註冊帳號的密碼後登入</div>\
					<div class="ezModal-middle">' +
					formWrapBlock('\
							'+oauthBlock(email)+'\
							'+passwordBlock(12)+'\
							'+captchaImgBlock()+'\
							'+resetPasswordLinkBlock()+'\
							'+largeButtonBlock("ezModal-oauth-button", "登入"))
					+'<input id="ezModal-socialTokenId" name="socialTokenId" type="hidden" value="'+tokenId+'" readonly/>\
					</form>\
					</div>\
				</div>';
	}
	function finalModalBlock(title,message){
		return '\
			<div class="ezModal-wrap">\
				<div class="ezModal-h1">'+title+'</div>\
				<div class="ezModal-h2-icon ezModal-icon-memFail">'+message+'</div>\
				<div class="ezModal-middle">\
					<div>\
						<button onclick="ezLogin.moveLoginModal()" class="ezModal-greenBtn ezModal-bsize15">以易遊網帳號登入</button>\
					</div>\
					<div style="margin-top:5px;">\
						<button onclick="ezLogin.hide()" class="ezModal-whiteBtn ezModal-bsize15">稍後再說</button>\
					</div>\
				</div>\
			</div>';
	}
	function loginModalBlock(){
		var hintLink = ezLogin.origin === "ORDER" ? 
			'<div>還沒有帳號嗎？<span id="ezModal-main-regLink" class="ezModal-greenLink">立即註冊</span></div>':'';
		var b2vTab = (ezLogin.origin === "ORDER" || ezLogin.disB2vLogin ) ?  "":'<div id="ezModal-main-nonMember-tab" class="ezModal-main-tab">訪客訂單</div>';
		var favDesc = favoriteDesc ? '<div class="ezModal-main-login-desc">收藏前請登入</div>':''; // 控管 收藏前請登入 敘述
		var discounCodeClaimDesc = claimDesc ? '<div class="ezModal-main-login-desc">領取折扣碼前請登入</div>':''
		return mainBlock('\
						<div class="ezModal-main-fullTab">\
							<div id="ezModal-main-login-tab" class="ezModal-main-tab ezModal-main-tab-focus">'+headerName+'</div>\
							<div id="ezModal-main-reg-tab" class="ezModal-main-tab">註冊</div>' + b2vTab + '\
						</div>\
						<div class="ezModal-main-login-block">'+ favDesc + discounCodeClaimDesc +formWrapBlock('\
						'+accountBlock("normalLogin")+'\
							'+loginStatusBlock()+'\
							'+largeButtonBlock("ezModal-login2Captcha-button", "登入"))
						+'</div>\
						<div class="ezModal-main-reg-block">'+formWrapBlock('\
							'+accountBlock("normalReg")+'\
							'+passwordRegBlock()+'\
							'+largeButtonBlock("ezModal-reg-button", "註冊")+'\
							')
						+'</div>\
						<div class="ezModal-main-nonMember-block">\
						<div class="ezModal-main-nonMember-desc">訪客訂單查詢與聯絡客服</div>'+formWrapBlock('\
						'+accountBlock("nonMember")+'\
						'+captchaImgUnfocusBlock()+'\
						'+largeButtonBlock("ezModal-nonMember-button", "查詢")+'\
						')
						+'</div>\
						<div class="ezModal-main-oauth-section">\
							<div class="clearfix ezModal-main-login-block">\
								<div class="ezModal-main-oauth-line"><span class="ezModal-main-oauth-or">或使用社群帳號登入</span></div>\
								<div class="ezModal-main-social-type">\
									<div class="ezModal-main-fbBtn ezModal-main-oauth-btn"></div>\
									<div class="ezModal-main-googleBtn ezModal-main-oauth-btn"></div>\
									<div class="ezModal-main-lineBtn ezModal-main-oauth-btn"></div>\
									<div class="ezModal-main-appleBtn ezModal-main-oauth-btn"></div>\
								</div>\
							</div>\
							<div class="clearfix ezModal-main-reg-block">\
								<div class="ezModal-main-oauth-line"><span class="ezModal-main-oauth-or">或使用社群帳號註冊</span></div>\
								<div class="ezModal-main-social-type">\
									<div class="ezModal-main-fbBtn ezModal-main-oauth-btn"></div>\
									<div class="ezModal-main-googleBtn ezModal-main-oauth-btn"></div>\
									<div class="ezModal-main-lineBtn ezModal-main-oauth-btn"></div>\
									<div class="ezModal-main-appleBtn ezModal-main-oauth-btn"></div>\
								</div>\
							</div>\
						</div>\
						<div class="ezModal-main-reg-block ezModal-main-reg-privataPolice">\
							註冊即同意<a class="ezModal-grayLink" target="_blank" href="'+ apiEnv.statement +'">會員約定事項</a>暨<a class="ezModal-grayLink" target="_blank" href="'+ apiEnv.privacy +'">隱私權保護政策</a>\
						</div>\
						<div class="ezModal-main-login-block ezModal-main-bottom">\
							<div>無法登入？<a href='+apiEnv.loginFAQ+' target="_blank" class="ezModal-greenLink">登入說明</a></div>\
							'+ hintLink +'\
						</div>\
						<div class="ezModal-main-reg-block ezModal-main-bottom">\
							<div>無法註冊？<a href='+apiEnv.regFAQ+' target="_blank" class="ezModal-greenLink">註冊說明</a></div>\
							<div>已經是易遊網會員？<span id="ezModal-main-loginLink" class="ezModal-greenLink">立即登入</span></div>\
						</div>\
						<div class="ezModal-main-nonMember-block ezModal-main-bottom">\
							<div><a href='+ apiEnv.nonMemberFAQ +' target="_blank" class="ezModal-greenLink">訪客購買說明</a></div>\
							<div>還沒有帳號嗎？<span id="ezModal-main-regLink" class="ezModal-greenLink">立即註冊</span></div>\
						</div>');
	}
	function captchaLoginPwdBlock(){
		return '\
				<div class="ezModal-wrap ezModal-main-loginPwd-block">\
					<div class="ezModal-h1">密碼登入</div>\
					<div class="ezModal-middle">\
					<div class="ezModal-captchaLogin-Pwd">會員帳號<br>'+ ezLogin.workspace.account +'</div>\
					<input type="hidden" id="ezModal-main-login-personId" value="'+ ezLogin.workspace.account +'">\
					<input type="hidden" id="ezModal-main-autoLogin" value="'+ ezLogin.workspace.autoLogin +'" readonly>\
						'+formWrapBlock('\
							'+passwordBlock(12)+'\
							'+captchaImgBlock()+'\
							'+largeButtonBlock("ezModal-loginPwd-button", "確認") )+'\
						<div class="ezModal-captchaLogin-Hint">\
							<span class="ezModal-greenLink" onclick="ezLogin.moveLoginModal()">改用其他帳號</span>\
							<span class="ezModal-greenLink" onclick="ezLogin.moveResetPswModal1()">忘記密碼</span>\
						</div>\
					</div>\
				</div>';
	}
	function loginVerifyBlock(){
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-h1 pt60">會員資料驗證</div>\
					<div class="ezModal-captchaLogin-verifyHints">為了提升帳戶使用安全，建議您進行以下資料驗證，驗證過的手機號碼/電子信箱，下次登入即可當作登入帳號使用\
						<p>（若使用手機號碼驗證，限台灣手機門號，若您在國外，請改用電子信箱驗證）</p>\
					</div>\
					<div id="ezModal-captchaLogin-verifyBtn">\
						<span class="ezModal-whiteBtn" data-type="mobile">手機號碼驗證</span>\
						<span class="ezModal-whiteBtn" data-type="email">電子信箱驗證</span>\
					</div>\
				</div>';
	}
	function captchaSendCode(type){
		if(type == "email") type = "email";
		if(type == "mobile") type = "msg";
		return '\
				<div id="ezModal-main-captchaAuthCode-loading" class="ezModal-main-authCode-hint ezModal-main-sending-'+ type +'">驗證碼發送中</div>\
				<div id="ezModal-main-captchaAuthCode" class="ezModal-main-reg-authCode">\
					<div class="ezModal-h1">驗證登入</div>\
					<div class="ezModal-main-reg-authCode-email ezModal-main-sent-'+ type +'">驗證碼已發送至<br>'+ ezLogin.workspace.authMaskAuthInfo +'</div>\
					<div class="ezModal-main-reg-authCode-content">\
						'+ authCodeFormBlock(6, "captchaCode") +'\
					</div>\
					<div class="ezModal-captchaLogin-Hint">\
						<span class="ezModal-greenLink" onclick="ezLogin.moveLoginModal()">改用其他帳號</span>\
						<span class="ezModal-greenLink" onclick="ezLogin.moveLoginPwdModal()">改用密碼登入</span>\
					</div>\
				</div>';
	}
	function mainAuthCodeModalBlock(type, options){
		if(type == "normal") type = "email";
		if(type == "cellphone") type = "msg";
		var returnPageHint = options == "nonMember" ? "變更信箱" : "變更信箱或手機號碼";

		return mainBlock('\
						<div id="ezModal-main-authCode-loading" class="ezModal-main-authCode-hint ezModal-main-sending-'+ type +'">驗證碼發送中</div>\
						<div id="ezModal-main-authCode" class="ezModal-main-reg-authCode">\
							<div class="ezModal-main-reg-authCode-email ezModal-main-sent-'+ type +'"></div>\
							<div class="ezModal-main-reg-authCode-content">\
								'+ authCodeFormBlock(6, options) +'\
							</div>\
							<div class="ezModal-authCode-hintLink ezModal-main-bottom ezModal-main-bottom-center">\
								<div>一直收不到驗證碼？\
									<span id="ezModal-main-changeAccount" class="ezModal-greenLink">'+ returnPageHint +'</span>\
								</div>\
							</div>\
						</div>');
	}
	function flowChartBlock(step, text){
		var addClass = {
			circle: "ezModal-steps-active",
			line: "ezModal-steps-active-line",
			both: "ezModal-steps-active ezModal-steps-active-line"
		};
		switch (step){
			case 1:
				addClass = {
					"one": addClass.circle,
					"two": "",
					"three": ""
				}
				break;
			case 2:
				addClass = {
					"one": addClass.both,
					"two": addClass.circle,
					"three": ""
				}
				break;
			case 3:
				addClass = {
					"one": addClass.both,
					"two": addClass.both,
					"three": addClass.circle
				}
				break;
		}
		return '<div class="ezModal-flowChart">\
							<div class="ezModal-steps-content ezModal-steps-content'+ step +'">'+ text +'</div>\
							<span class="ezModal-steps '+ addClass.one +'">1</span>\
							<span class="ezModal-steps '+ addClass.two +'">2</span>\
							<span class="ezModal-steps '+ addClass.three +'">3</span>\
							<span class="ezModal-steps ezModal-steps-complete">&nbsp;</span>\
						</div>'
	}
	function resetPswStep1Block(){
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-h1">重設密碼</div>\
					'+ flowChartBlock(1, "確認帳號") +'\
					<div class="ezModal-middle ezModal-resetPswStep1">\
						'+formWrapBlock('\
						'+accountBlock("normalResetPsw")+'\
							'+captchaImgBlock()+'\
							'+largeButtonBlock("ezModal-resetPsw1-button", "下一步"))+'\
						<div class="ezModal-resetPswHint">如果註冊信箱已無法使用，請<a href="https://www.eztravel.com.tw/information/contactus/" class="ezModal-greenLink" target="_blank">聯絡我們</a></div>\
					</div>\
				</div>';
	}
	function resetPswStep2Block(email){
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-h1">重設密碼</div>\
					'+ flowChartBlock(2, "驗證帳號") +'\
					<div class="ezModal-resetPsw-authCode-email">驗證碼已發送至<br>'+email+'</div>\
					<div class="ezModal-middle ezModal-resetPswStep2">\
						'+ authCodeFormBlock(6, "resetPsw") +'\
					</div>\
				</div>';
	}
	function resetPswStep3Block(){
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-h1">重設密碼</div>\
					'+ flowChartBlock(3, "設新密碼") +'\
					<div class="ezModal-resetPsw-content">請輸入新密碼</div>\
					<div class="ezModal-middle ezModal-resetPswStep3">' +
					formWrapBlock('\
						'+setPasswordBlock()+'\
						'+largeButtonBlock("ezModal-resetPsw3-button", "送出"))
					+'</div>\
				</div>';
	}
	function resetPswStep4Block(){
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-h1">密碼變更成功</div>\
					<div class="ezModal-h2-icon ezModal-icon-resetpsw-success">您已成功設定新密碼\
						 <div class="ezModal-resetPsw-successHint">(為了您帳戶安全，已登出所有裝置。)</div>\
					</div>\
					<div class="ezModal-btn-center">\
						<button class="ezModal-greenBtn ezModal-mediumBtn" onclick="ezLogin.moveLoginModal()">返回登入</button>\
					</div>\
				</div>';
	}
	function authCodeOverloadBlock(type, minutes){
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-authCodeError ezModal-'+ type +'-sentFail">驗證碼重送次數已達上限<br>請於 '+ minutes +' 分鐘後重新註冊</div>\
				</div>';
	}
	function authCodeFailedBlock(type){
		text = type == "mobile"? "手機網路":"信箱";
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-authCodeError ezModal-'+ type +'-sentFail">驗證碼發送異常<br>請檢查'+ text +'狀態後重試，謝謝</div>\
				</div>';
	}
	function systemBusyBlock(errMsgSec){
		errMsg = errMsgSec ? "驗證碼系統忙碌中，請於 <span width=15>"+ errMsgSec +"</span> 秒後重新嘗試，謝謝" : "系統忙碌中，請稍後再試";
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-systemBusy">'+ errMsg +'</div>\
				</div>';
	}
	function socialReg(socialBaseInfo){
		var imagePath = SOURCE_REF + 'assets/images/common/mem-modal/';
		var iconHtml = '';
		var personHtml = '';
		var socialTypeImage = '';
		var personIcon = '';
		var radius = '';
		if(socialBaseInfo.picture == "") personHtml = '<div class="ezModal-socialReg-newMemIcon"></div>';
		else{
			if(socialBaseInfo.socialType == "GOOGLE") socialTypeImage = imagePath + "imageIcon-google.svg"
			if(socialBaseInfo.socialType == "FB") socialTypeImage = imagePath + "imageIcon-fb.svg"
			if(socialBaseInfo.socialType == "LINE") socialTypeImage = imagePath + "imageIcon-line.svg"
			if(socialBaseInfo.socialType == "APPLE") socialTypeImage = imagePath + "imageIcon-apple.svg"
			personIcon = socialBaseInfo.picture;
			radius = "border-radius: 50%";
			personHtml = '<img class="ezModal-socialReg-image" src="'+ personIcon +'" style="'+ radius +'">';
			iconHtml = '<img class="ezModal-socialReg-socialIcon" src="'+ socialTypeImage +'"></img>';
		}
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-h1">社群帳號註冊</div>\
					<div class="ezModal-socialReg-wrap">\
						<div class="ezModal-socialReg">\
							'+ iconHtml +'\
							'+ personHtml +'\
							<span>我是新會員</span>\
							<div class="ezModal-socialReg-desc">從未註冊過易遊網帳號<br>立即使用社群帳號加入會員</div>\
							<button class="ezModal-greenBtn" id="ezModal-socialReg-newMem">註冊</button>\
						</div>\
						<div class="ezModal-socialReg-hr"></div>\
						<div class="ezModal-socialReg">\
							<div class="ezModal-socialReg-ezMemIcon"></div>\
							<span>我已是易遊網會員</span>\
							<div class="ezModal-socialReg-desc">曾在易遊網註冊<br>於登入後進行社群帳號綁定</div>\
							<button class="ezModal-greenBtn" id="ezModal-socialReg-ezMem">登入</button>\
						</div>\
					</div>\
				</div>';
	}
	function socialBind(){  //社群註冊
		return '\
				<div class="ezModal-wrap">\
					<div class="ezModal-h1">帳號綁定</div>\
					<div class="ezModal-h2">以易遊網會員身份登入後即可完成綁定</div>\
					<div class="ezModal-main-login-block ezModal-socialBind">'+formWrapBlock('\
						'+accountBlock("normalLogin")+'\
						'+passwordBlock(12)+'\
						'+captchaImgBlock()+'\
						'+resetPasswordLinkBlock()+'\
						'+largeButtonBlock("ezModal-login-button", "登入"))
					+'</div>\
				</div>';
	}

	// function socialPrivate(){  // Apple 隱私信箱 modal
	// 	return '\
	// 			<div class="ezModal-wrap">\
	// 				<div class="ezModal-h1">Apple 註冊成功</div>\
	// 				<div class="ezModal-socialPrivate-wrap">\
	// 					<img class="ezModal-socialPrivate-image" src="'+ SOURCE_REF +'assets/images/common/mem-modal/social-privateMail.svg">\
	// 					<span>為了保障您的帳戶資料安全，<br>建議前往會員中心認證電子信箱。</span>\
	// 					<div class="ezModal-socialBind">'
	// 					+ largeButtonBlock("ezModal-private-button", "繼續")		
	// 					+'</div>\
	// 					</div>\
	// 				</div>';
	// }
	/*-------------------------  CHECK FORM  -------------------------*/
	/**check form邏輯:
	 * 登入頁input只檢查空值(帳號擋中文)
	 * 格式即時驗證、空值submit才驗證
	 * 註冊input maxlength會多1 以免使用者輸入過長資料沒檢查到
	 * 其餘input maxlength皆為剛好長度
	 */
	// 舊版一般登入
	function loginCheckForm(loginAction){
		ezLogin.clearError("ALL");//清除所有錯誤欄位
		
		if($.trim($("#ezModal-main-login-personId").val())==""){
			ezLogin.setErrorrHtml("ezModal-main-login-personId","請輸入帳號","change_blank");
		}else{
			var loginType = checkType("ezModal-main-login-personId") == 'typePhone' ? 'cellphone' : 'normal'
			if(loginType == 'cellphone') checkCellphone("ezModal-main-login-personId");
		}
		 
		if(loginAction == 'login'){
			if($.trim($("#ezModal-login-password").val())==""){
				ezLogin.setErrorrHtml("ezModal-login-password","請輸入密碼","change_blank");
			}
			checkCaptcha();
		}

		$(".ezModal-error-input input").eq(0).trigger("focus");
		modalAutoHeight();

		if($("#ezModal-main .ezModal-main-login-block .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			loginAction == 'login'? captchaCheck('login', loginType) : captchaLoginCheck()
		}
	}
	// 新版驗證碼登入
	function loginPwdCheckForm(e){
		e.preventDefault();//取消預設
		ezLogin.clearError("ALL");//清除所有錯誤欄位
		 
		if($.trim($("#ezModal-login-password").val())==""){
			ezLogin.setErrorrHtml("ezModal-login-password","請輸入密碼","change_blank");
		}
		checkCaptcha();
		$(".ezModal-error-input input").eq(0).trigger("focus");

		if($("#ezModal-main .ezModal-main-loginPwd-block .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			captchaCheck('login')
		}
	}
	function regCheckForm(e){
		e.preventDefault();//取消預設
		ezLogin.clearError("ALL");//清除所有錯誤欄位
		$(".ezModal-hint-msg").remove();

		var regType = "";
		var valRegPersonId = $.trim($("#ezModal-main-reg-personId").val());

		if(valRegPersonId==""){
			ezLogin.setErrorrHtml("ezModal-main-reg-personId","請輸入帳號","change_blank");
		} else {
			regType = checkType("ezModal-main-reg-personId");
			if( regType =="typePhone"){
				checkCellphone("ezModal-main-reg-personId");
				regType = "cellphone";
			}else if (regType =="typeEmail"){
				checkBadEmail("ezModal-main-reg-personId");
				regType = "normal";
			}else{
				ezLogin.setErrorrHtml("ezModal-main-reg-personId","請輸入電子信箱或手機號碼註冊","change_blank");
			}
		}
		checkPsw("ezModal-reg-password");
		if($.trim($("#ezModal-reg-password").val())==""){
			ezLogin.setErrorrHtml("ezModal-reg-password","請輸入密碼","change_blank");
		}

		$(".ezModal-error-input input").eq(0).trigger("focus");
		modalAutoHeight();
		if($("#ezModal-main .ezModal-main-reg-block .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			accountCheck("reg", regType);
		}
	}
	function resetPswStep1CheckForm(e){
		e.preventDefault();//取消預設
		ezLogin.clearError("ALL");//清除所有錯誤欄位

		var resetPswType = "";

		var valResetPsw = $.trim($("#ezModal-main-resetPsw-personId").val());

		if(valResetPsw ==""){
			ezLogin.setErrorrHtml("ezModal-main-resetPsw-personId","請輸入帳號","change_blank");
		}else{
			resetPswType = checkType("ezModal-main-resetPsw-personId");
			
			switch (resetPswType) {
				case "typePhone":
					checkCellphone("ezModal-main-resetPsw-personId");
					resetPswType = "cellphone";
					break;
				
				case "typeEmail":
					checkBadEmail("ezModal-main-resetPsw-personId");
					resetPswType = "email";
					break;
				
				case "typePersonId":
					resetPswType = "personId";
					break;
			}
		}
		checkCaptcha();
		$(".ezModal-error-input input").eq(0).trigger("focus");
		if($("#ezModal-main .ezModal-resetPswStep1 .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			captchaCheck("resetPsw", resetPswType);
		}
	}
	function resetPswStep3CheckForm(e){
		e.preventDefault();//取消預設
		ezLogin.clearError("ALL");//清除所有錯誤欄位

		checkPsw("ezModal-reset-password1");
		checkPswConfirm("ezModal-reset-password2");

		if($.trim($("#ezModal-reset-password1").val())==""){
			ezLogin.setErrorrHtml("ezModal-reset-password1","請輸入密碼","change_blank");
		}else{
			if($.trim($("#ezModal-reset-password2").val())==""){
				ezLogin.setErrorrHtml("ezModal-reset-password2","請重複確認密碼","change_blank");
			}
		}
		
		$(".ezModal-error-input input").eq(0).trigger("focus");
		
		if($("#ezModal-main .ezModal-resetPswStep3 .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			checkresetPswAuthCode();
		}
	}
	function oauthCheckForm(e){
		e.preventDefault();//取消預設
		ezLogin.clearError("ALL");//清除所有錯誤欄位
		
		checkPsw("ezModal-login-password");

		if($("#ezModal-login-password").val() == "") {
			ezLogin.setErrorrHtml("ezModal-login-password","請輸入密碼","change_blank");
		}

		checkCaptcha();
		$(".ezModal-error-input input").eq(0).trigger("focus");
		if ($("#ezModal-main .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			bindSocialAjax();
		}
	}
	function nonMemberCheckForm(e){
		e.preventDefault();//取消預設
		ezLogin.clearError("ALL");//清除所有錯誤欄位

		if($.trim($("#ezModal-main-nonMember").val())==""){
			ezLogin.setErrorrHtml("ezModal-main-nonMember","請輸入 e-mail","change_blank");
		}

		checkCaptcha();
		$(".ezModal-error-input input").eq(0).trigger("focus");

		if($("#ezModal-main .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			captchaCheck("nonMember");
		}
	}
	function socialBindCheckForm(e){
		e.preventDefault();//取消預設
		ezLogin.clearError("ALL");//清除所有錯誤欄位

		var loginType = "";
		
		if($.trim($("#ezModal-main-login-personId").val())==""){
			ezLogin.setErrorrHtml("ezModal-main-login-personId","請輸入帳號","change_blank");
		} else {
			loginType = checkType("ezModal-main-login-personId");
			if( loginType =="typePhone"){
				checkCellphone("ezModal-main-login-personId");
				loginType = "cellphone";
			}else {
				loginType = "normal";
			}
		}
		if($.trim($("#ezModal-login-password").val())==""){
			ezLogin.setErrorrHtml("ezModal-login-password","請輸入密碼","change_blank");
		}

		checkCaptcha();
		$(".ezModal-error-input input").eq(0).trigger("focus");
		modalAutoHeight(440);

		if($("#ezModal-main .ezModal-wrap .ezModal-error-input").length === 0) {//假如沒有錯誤
			changeBtnStatToLoading();
			captchaCheck("socialBind", loginType);
		}
	}
	function checkPsw(_this){
		var val = $.trim($("#"+_this).val());
		var errMsg = "";
		
		if(val !== ""){ //空值不檢查
			if(val.length<6 || val.length>12){
				errMsg = "密碼長度為 6 ~ 12 碼";
			}else if(!/^[A-Za-z0-9]+$/.test(val)){
				errMsg = "密碼只能為大小寫英文、數字";
			}
			if(errMsg) ezLogin.setErrorrHtml(_this, errMsg, "change_blank");
		}
	}
	function checkPswConfirm(_this){
		var val1 = $.trim($("#ezModal-reset-password1").val());
		var val2_confirm = $.trim($("#"+_this).val());

		if(val2_confirm !== ""){
			if(val1 !== val2_confirm){
				ezLogin.setErrorrHtml(_this, "密碼不相符", "change_blank");
			}
		}
	}
	function checkCellphone(_this){
		var val = $.trim($("#"+_this).val());
		var errMsg = "";

		if(val !== ""){
			if(val.length !== 10){
				errMsg = "手機號碼長度錯誤";
			}else if(val.indexOf("09") !== 0 || !/^[0-9]*$/.test(val)){
				errMsg = "手機號碼格式錯誤";
			}
			if(errMsg) ezLogin.setErrorrHtml(_this, errMsg, "change_blank");
		}
	}
	function checkBadEmail(_this){
		$(".ezModal-hint-msg").remove();
		var emailExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		var val = $.trim($("#"+_this).val());
		if(emailExp.test(val)){
			var encodeResult = encode(val);
			var accountCheckUrl = getMemberEndPoint() +'auth/checkEmail?&hash='+ encodeResult.hash +'&opt='+ encodeResult.opt;
			$.ajax({
				type: "GET",
				async: false,
				dataType: 'json',
				url: accountCheckUrl,
				xhrFields: {
					withCredentials: true
				},
				success: function(data){
					if(data.status == 1) ezLogin.setErrorrHtml(_this,data.statusValue,"change_blank");
					if(data.status == 2) $("#"+_this).parent().after('<div class="ezModal-hint-msg">'+ data.statusValue +'</div>');
				},
				timeout: 30000
			});
		}else ezLogin.setErrorrHtml(_this,"電子信箱格式不符","change_blank");
	}
	function checkType (_this) {
 
		var val = $.trim($("#"+_this).val());

		if(val !== ""){
			if( /^09[0-9]*$/.test(val)){
				return "typePhone";
			}else if (val.indexOf("@") > -1){
				return "typeEmail";
			}else {
				return "typePersonId";
			}
		}
}
	function checkCaptcha(){
		if(document.getElementById("ezModal-captcha-response")){
			document.getElementById("ezModal-captcha-response").value=$.trim(document.getElementById("ezModal-captcha-response").value)
			if(document.getElementById("ezModal-captcha-response").value==""){
				ezLogin.setErrorrHtml("ezModal-captcha-response","請輸入圖片中的文字","change_blank");
			}
		}
	}
	function changeBtnStatToLoading(){
		$("#ezModal-main .ezModal-greenBtn").prop("disabled",true).addClass("disabled loading");
	}
	function changeBtnStatToNormal(){
		$("#ezModal-main .ezModal-greenBtn").prop("disabled",false).removeClass("disabled loading");
	}
	// function privateContinue(){
	// 	SocialNextStep(ezLogin.workspace.socialBaseInfo);
	// }
	/*-------------------------  BIND EVENT  -------------------------*/
	function allPageBind(){
		var keydownCapsLocks=[]
		$(document)
		//input focus blur
		.on("focus.ezModal-event","#ezModal-main .ezModal-use_focus",function(){
			$(this).closest(".ezModal-input-box").addClass("ezModal-input-focus");
		})
		.on("blur.ezModal-event","#ezModal-main .ezModal-use_focus",function(){
			$(this).closest(".ezModal-input-box").removeClass("ezModal-input-focus");
		})
		.on("blur.ezModal-event","#ezModal-main .ezModal-detect-full",function(e){
			detectFullWidthChar(e.target.value) ? e.target.value = transferFullWidthToHalfWidth(e.target.value) : function(){return false}
		})
		.on("keydown.ezModal-event","#ezModal-main .ezModal-use_focus",function(e){
			if( ezModal_layoutEnv() === 'PC'){
				detectCapsLock(e.originalEvent) 
  					? $(".caps-lock-hint").length > 0 
    					? capsLockOn(timeoutID = setTimeout(function(){},0))(keydownCapsLocks)(clearLastTimeShow)
    					: capsLockOn(timeoutID = setTimeout(function(){},0))(keydownCapsLocks)(insertCapsLockHint)
  					: $(".caps-lock-hint").removeClass("show");
			}
		})
	}
	function memberBind(){
		pswEyeBind();
		$(document)
		//login reg tab
		.on("click.ezModal-event","#ezModal-main #ezModal-main-login-tab",function(){tabSwitch("login")})
		.on("click.ezModal-event","#ezModal-main #ezModal-main-reg-tab",function(){tabSwitch("reg")})
		.on("click.ezModal-event","#ezModal-main #ezModal-main-nonMember-tab",function(){tabSwitch("nonMember")})
		//login reg bottom hint to tab
		.on("click.ezModal-event","#ezModal-main #ezModal-main-loginLink",function(){tabSwitch("login")})
		.on("click.ezModal-event","#ezModal-main #ezModal-main-regLink",function(){tabSwitch("reg")})
		.on("click.ezModal-event","#ezModal-main #ezModal-main-nonMemberLink",function(){tabSwitch("nonMember")})
		//checkform
		.on("click.ezModal-event","#ezModal-login-button",function(e){
			e.preventDefault();
			loginCheckForm("login")
		})
		.on("click.ezModal-event","#ezModal-login2Captcha-button",function(e){
			e.preventDefault();
			loginCheckForm("captchaLogin")
		})
		.on("click.ezModal-event","#ezModal-reg-button",regCheckForm)
		.on("click.ezModal-event","#ezModal-nonMember-button",nonMemberCheckForm)
		//social btn
		.on("click.ezModal-event","#ezModal-main .ezModal-main-login-block .ezModal-main-fbBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("fb", "login"))
				: ezLogin.popup(apiEnv.socialMedia("fb", "login"), "fb");
		})
		.on("click.ezModal-event","#ezModal-main .ezModal-main-login-block .ezModal-main-googleBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("google", "login"))
				: ezLogin.popup(apiEnv.socialMedia("google", "login"), "google");
		})
		.on("click.ezModal-event","#ezModal-main .ezModal-main-login-block .ezModal-main-lineBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("line", "login"))
				: ezLogin.popup(apiEnv.socialMedia("line", "login"), "line");
		})
		.on("click.ezModal-event","#ezModal-main .ezModal-main-login-block .ezModal-main-appleBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("apple", "login"))
				: ezLogin.popup(apiEnv.socialMedia("apple", "login"), "apple");
		})
		.on("click.ezModal-event","#ezModal-main .ezModal-main-reg-block .ezModal-main-fbBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("fb", "reg"))
				: ezLogin.popup(apiEnv.socialMedia("fb", "reg"), "fb");
		})
		.on("click.ezModal-event","#ezModal-main .ezModal-main-reg-block .ezModal-main-googleBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("google", "reg"))
				: ezLogin.popup(apiEnv.socialMedia("google", "reg"), "google");
		})
		.on("click.ezModal-event","#ezModal-main .ezModal-main-reg-block .ezModal-main-lineBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("line", "reg"))
				: ezLogin.popup(apiEnv.socialMedia("line", "reg"), "line");
		})
		.on("click.ezModal-event","#ezModal-main .ezModal-main-reg-block .ezModal-main-appleBtn",function(){
			ezLogin.noWindowSocialLogin()
				? ezLogin.genBrowseInfo(apiEnv.socialMedia("apple", "reg"))
				: ezLogin.popup(apiEnv.socialMedia("apple", "reg"), "apple");
		})
		//captcha refresh btn
		.on("click.ezModal-event","#ezModal-captcha-refresh",genCaptchaImg)
		//real-time checkform
		.on("blur.ezModal-event","#ezModal-main #ezModal-main-reg-personId",function(){
			$(".ezModal-hint-msg").remove();
			if ($("#ezModal-main-reg-personId").val().indexOf("@") > -1) checkBadEmail(this.id);
		})
		.on("blur.ezModal-event","#ezModal-main #ezModal-reg-password",function(){
			checkPsw(this.id);
		})
	}
	function regAuthCodeBind(){
		authCodeBind();
		$(document)
		//changeAccount click
		.on("click.ezModal-event","#ezModal-main-changeAccount",function(){
			ezLogin.moveLoginModal("reg", "unlock");
		})
		//resend auth code btn
		.on("click.ezModal-event",".ezModal-resendAuthCodeBtn",function(e){
			e.preventDefault(); //取消預設
			ezLogin.authCodeCount();
			ezLogin.workspace = {
				status: "re",
				action: $(".ezModal-resendAuthCodeBtn").attr("data-action"),
				type: ezLogin.workspace.authCodeType,
				account: ezLogin.workspace.account
			}
			sendAuthCode();
		})
		//auth code submit
		.on("click.ezModal-event","#ezModal-reg-authCode-button",function(e){
			e.preventDefault();
			$(".ezModal-authCode input").removeClass("ezModal-error-input");
			$("#ezModal-authCode-error").hide();
			changeBtnStatToLoading();
			checkRegAuthCode();
		})
	}
	function oauthBind(){
		pswEyeBind();
		$(document)
		//captcha refresh btn
		.on("click.ezModal-event","#ezModal-captcha-refresh",genCaptchaImg)
		//checkform
		.on("click.ezModal-event","#ezModal-oauth-button",oauthCheckForm)
		//real-time checkform
		.on("blur.ezModal-event","#ezModal-main #ezModal-login-password",function(){
			checkPsw(this.id);
		})
	}
	function resetPsw1Bind(){
		$(document)
		//captcha refresh btn
		.on("click.ezModal-event","#ezModal-captcha-refresh",genCaptchaImg)
		//checkform
		.on("click.ezModal-event","#ezModal-main #ezModal-resetPsw1-button",resetPswStep1CheckForm)
		//input enter submit
		.on("keypress.ezModal-event","#ezModal-main .ezModal-resetPswStep1 input",function(e){
			if(e.keyCode == 13) $("#ezModal-resetPsw1-button").trigger( "click" );
		})
	}
	function resetPsw2Bind(){
		authCodeBind();
		$(document)
		//resend auth code btn
		.on("click.ezModal-event",".ezModal-resendAuthCodeBtn",function(e){
			e.preventDefault();	//取消預設
			ezLogin.authCodeCount();
			ezLogin.workspace = {
				status: "re",
				action: $(".ezModal-resendAuthCodeBtn").attr("data-action"),
				type: ezLogin.workspace.type, 
				account: ezLogin.workspace.account,
				captchaval: ezLogin.workspace.captchaval,
				captchakey: ezLogin.workspace.captchakey
			}
			sendAuthCode();
		})
		//auth code submit
		.on("click.ezModal-event","#ezModal-resetPsw-authCode-button",function(e){
			e.preventDefault();
			$(".ezModal-authCode input").removeClass("ezModal-error-input");
			$("#ezModal-authCode-error").hide();
			changeBtnStatToLoading();
			checkAuthCode();
		})
	}
	function resetPsw3Bind(){
		$(document)
		//checkform
		.on("click.ezModal-event","#ezModal-main #ezModal-resetPsw3-button",resetPswStep3CheckForm)
		//input enter submit
		.on("keypress.ezModal-event","#ezModal-main .ezModal-resetPswStep3 input",function(e){
			if(e.keyCode == 13) $("#ezModal-resetPsw3-button").trigger( "click" );
		})
		//real-time checkform
		.on("blur.ezModal-event","#ezModal-main #ezModal-reset-password1",function(){
			checkPsw(this.id);
		})
		.on("blur.ezModal-event","#ezModal-main #ezModal-reset-password2",function(){
			checkPswConfirm(this.id);
		})
	}
	function nonMemAuthCodeBind(){
		authCodeBind();
		$(document)
		//changeAccount click
		.on("click.ezModal-event","#ezModal-main-changeAccount",function(){
			ezLogin.moveLoginModal("nonMember", "unlock");
		})
		//resend auth code btn
		.on("click.ezModal-event",".ezModal-resendAuthCodeBtn",function(e){
			e.preventDefault(); //取消預設
			ezLogin.authCodeCount();
			sendCode("re");
		})
		//auth code submit
		.on("click.ezModal-event","#ezModal-nonMember-authCode-button",function(e){
			e.preventDefault();
			$(".ezModal-authCode input").removeClass("ezModal-error-input");
			$("#ezModal-authCode-error").hide();
			changeBtnStatToLoading();
			verCode();
		})
	}
	function pswEyeBind(){
		$(document)
		//login show psw
		.on("click.ezModal-event","#ezModal-pass-eye-login",function(){passwordEye("login")})
		//reg show psw
		.on("click.ezModal-event","#ezModal-pass-eye-reg",function(){passwordEye("reg")})
		//hover show psw
		.on("mouseenter.ezModal-event","#ezModal-pass-eye-login",function(){
			$("#ezModal-pass-eye-login").addClass("hover").on("mouseleave",function(){
				$(this).removeClass("hover").off("mouseleave");
			})
		})
		.on("mouseenter.ezModal-event","#ezModal-pass-eye-reg",function(){
			$("#ezModal-pass-eye-reg").addClass("hover").on("mouseleave",function(){
				$(this).removeClass("hover").off("mouseleave");
			})
		})
	}
	function authCodeBind(){
		$(document)
		//default select
		.on("focus.ezModal-event","#ezModal-main .ezModal-authCode input",function(){
			$(this).trigger('select');
		})
		//自動換格、按鈕偵測、input vlaue整理
		.on("input.ezModal-event","#ezModal-main .ezModal-authCode input",function(){
			$(".ezModal-authCode-input input").removeClass("ezModal-error-input");
			$("#ezModal-authCode-error").hide();
			var btnStatus = true, inputLength = parseInt($(this).attr('maxlength'));
			if ($.trim($(".ezModal-authCode input:nth-child(1)").val()).length < inputLength ) btnStatus = false;
			if(btnStatus){
				$(".ezModal-greenBtn").removeClass("disabled").prop("disabled",false);
				var completeAuthCode = "";
				completeAuthCode = $(".ezModal-authCode input:nth-child(1)").val();
				$("#ezModal-completeAuthCode").attr("value", completeAuthCode);
			}else{
				$(".ezModal-greenBtn").addClass("disabled").attr("disabled", "true");
				$("#ezModal-completeAuthCode").attr("value", "");
			}
		})
	}
	function socialRegBind(){
		$(document)
		// 我是新會員
		.on("click.ezModal-event","#ezModal-socialReg-newMem",function(){
			socialReg_newMem();
		})
		// 我已是易遊網會員
		.on("click.ezModal-event","#ezModal-socialReg-ezMem",function(){
			ezLogin.moveSocialBindModal();
		})
	}
	function socialBindBind(){
		pswEyeBind();
		$(document)
		//checkform
		.on("click.ezModal-event","#ezModal-login-button",socialBindCheckForm)
		//captcha refresh btn
		.on("click.ezModal-event","#ezModal-captcha-refresh",genCaptchaImg)
		
	}
	function captchaPwdBind(){
		pswEyeBind();
		$(document)
		.on("click.ezModal-event","#ezModal-captcha-refresh",genCaptchaImg)
		.on("click.ezModal-event","#ezModal-loginPwd-button",loginPwdCheckForm)
	}
	function captcha2CodeBind(){
		authCodeBind()
		$(document)
		//resend auth code btn
		.on("click.ezModal-event",".ezModal-resendAuthCodeBtn",function(e){
			e.preventDefault();	//取消預設
			ezLogin.authCodeCount();
			captchaLoginCheck(true);
		})
		//auth code submit
		.on("click.ezModal-event","#ezModal-captchaCode-authCode-button",function(e){
			e.preventDefault();
			$(".ezModal-authCode input").removeClass("ezModal-error-input");
			$("#ezModal-authCode-error").hide();
			changeBtnStatToLoading();
			checkCaptchaCode();
		})
	}
	function vertifyBind(){ 
		$(document)
		.on("click.ezModal-event","#ezModal-captchaLogin-verifyBtn span",function(e){
			e.preventDefault(); 
			if(ezModal_layoutEnv() === 'MWEB'){
				// 為了讓 mweb 的 login icon refresh，先提前呼叫 headerRefresh
				getLoginUserData(function (data) {
					if (window.headerRefresh) headerRefresh(data); // 首頁預設執行
					checkeHeaderRefresh();   //check 是否無法呼叫 headerRefresh   
				})
			}
			// ezLogin hide 時會觸發 ezModal hiddenCallback
			// hide 的時候先不執行 hiddenCallback
			var tempFn = function(){}
			if(typeof ezModal_hiddenCallback==='function'){
				tempFn = ezModal_hiddenCallback
				ezModal_hiddenCallback = ''
			}
			ezLogin.hide() 
			setTimeout(function(){
				ezModal_hiddenCallback = tempFn
				ezVertify.show({ type: e.target.dataset.type , 
					onComplete: function(){
						loginSuccess()
					},
				}) 
			},500)
		})
		.on("click.ezModal-event",".G-ezModal-modal-close-btn",function(e){
			loginSuccess()
		})

		// 除了會員大首會被導走外，其餘點選背景接續後面登入流程 
		if( window.location.pathname.indexOf('/member/login') ===-1 && ezModal_layoutEnv() === 'PC'){
			window.ezModal_hiddenCallback = function(){
				loginSuccess()
			}
		} 
	}
	// function socialPrivateBind(){
	// 	$(document)
	// 	// SocialNextStep
	// 	.on("click.ezModal-event","#ezModal-private-button",privateContinue)
	// 	// cancel button use callback function
	// 	window.ezModal_hiddenCallback = function(){
	// 		privateContinue();
	// 	}
	// }
	function bindEvent(status, page){
		if(status=="off"){
			$(document).off(".ezModal-event");
		}else{
			$(document).off(".ezModal-event");
			allPageBind();
			switch (page){
				case "main":
					memberBind();
					break;
				case "regAuthCode":
					regAuthCodeBind();
					break;
				case "oauth":
					oauthBind();
					break;
				case "resetPsw1":
					resetPsw1Bind();
					break;
				case "resetPsw2":
					resetPsw2Bind();
					break;
				case "resetPsw3":
					resetPsw3Bind();
					break;
				case "nonMemAuthCode":
					nonMemAuthCodeBind();
					break;
				case "socialRegBind":
					socialRegBind();
					break;
				case "socialBindBind":
					socialBindBind();
					break;
				case "captchaPwd":
					captchaPwdBind();
					break;
				case "captcha2Code":
					captcha2CodeBind();
					break;
				case "vertify":
					vertifyBind();
					break
				// case "socialPrivate":
				// 	socialPrivateBind();
				// 	break;
			}
		}
	}
	function passwordEye(option){
		$("#ezModal-pass-eye-"+option).toggleClass("ezModal-eye-show").removeClass("hover");
		if($("#ezModal-"+option+"-password").attr("type")=="text"){
			$("#ezModal-"+option+"-password").attr("type","password");
		}else{
			$("#ezModal-"+option+"-password").attr("type","text");
		}
	}
	function detectCapsLock(event){
		return typeof event.getModifierState === "function" ?  event.getModifierState('CapsLock') : false;
	}
	function detectFullWidthChar(str) {
		return /[^\x00-\xff]/g.test(str)
	}
	function transferFullWidthToHalfWidth(str){
		var ascii = '';
		for(var i=0, l=str.length; i<l; i++) {
			var c = str[i].charCodeAt(0);
			if (c >= 0xFF00 && c <= 0xFFEF) {
			   c = 0xFF & (c + 0x20);
			}
			ascii += String.fromCharCode(c);
		}
		return ascii;
	}
	function capsLockHintHtml(hint){
		return '<span class="caps-lock-hint">'+hint+'</span>';
	}
	function insertCapsLockHint(time){
		$(".ezModal-main-fullTab").append(capsLockHintHtml("大寫鎖定"));
		$(".caps-lock-hint").addClass("show t"+time);
		setTimeout(function(){ $(".caps-lock-hint.show.t"+time).removeClass("show t"+time);},5000)
	}
	function clearLastTimeShow(timeoutID){
        $(".caps-lock-hint").addClass("show t"+ timeoutID);
        setTimeout(function(){  $(".caps-lock-hint.show.t"+timeoutID.toString()).removeClass("show t"+ timeoutID.toString());},5000)       
    }
	var capsLockOn = function(time) {
        return function(keydownCapsLocks){
            keydownCapsLocks.push(time);
            keydownCapsLocks.slice(0,-1).forEach(function(i){
				$(".caps-lock-hint.t"+i.toString()).removeClass("t"+i.toString());
                clearTimeout(i)
            }) 
            return function(cb) {
                return cb(time);
            };
        }  
    };
	function modalAutoHeight(height){
		height = height || 480; // 預設為main480px
		var errCount = $(".ezModal-error-msg").length;
		if($("#ezModal-main-error").html() == "") errCount -= 1;
		var addHeight = errCount * 20;
		$("#ezModal-main").css("height", height + addHeight);
	}
	function tabSwitch(option,captchaDone){
		var initOption = ["login","reg","nonMember"];
		var opposite = initOption.filter(function(item){
			return item != option;
		});
		
		$(".ezModal-main-tab").removeClass("ezModal-main-tab-focus");
		$("#ezModal-main-" + option + "-tab").addClass("ezModal-main-tab-focus");
		ezLogin.clearError("ALL");
		$(".ezModal-main-" + option + "-block").show();
		for(var index = 0 ; index < opposite.length ; index++){
			$(".ezModal-main-" + opposite[index] + "-block").hide();
		}
		
		// switch captcha's block focus/unfocus
		var focusOption = ["login","nonMember"]; // 目前一個畫面有兩個 captch 需輸入
		var captchaOpp = focusOption.filter(function(item){
			return item != option;
		});
		
		// focus -> unfocus
		$('.ezModal-main-'+ captchaOpp +'-block #ezModal-captcha-response').attr('id','ezModal-captcha-response-unfocus')
		$('.ezModal-main-'+ captchaOpp +'-block #ezModal-captchaImg').attr('id','ezModal-captchaImg-unfocus')
		$('.ezModal-main-'+ captchaOpp +'-block #ezModal-captcha-refresh').attr('id','ezModal-captcha-refresh-unfocus')
		$('.ezModal-main-'+ captchaOpp +'-block #ezModal-captchakey').attr('id','ezModal-captchakey-unfocus')
		// unfocus -> focus 
		$('.ezModal-main-'+ option +'-block #ezModal-captcha-response-unfocus').attr('id','ezModal-captcha-response')
		$('.ezModal-main-'+ option +'-block #ezModal-captchaImg-unfocus').attr('id','ezModal-captchaImg')
		$('.ezModal-main-'+ option +'-block #ezModal-captcha-refresh-unfocus').attr('id','ezModal-captcha-refresh')
		$('.ezModal-main-'+ option +'-block #ezModal-captchakey-unfocus').attr('id','ezModal-captchakey')
		
		// 已產生過 captchaImg (包含從 this.moveLoginModal )，不用再重新產生 
		// 為了產生 tabswitch前 為 -unfocus 的驗證圖片
		if ( !captchaDone && $("#ezModal-captchakey").val() == "") genCaptchaImg();		
		modalAutoHeight();
	}

	/*-------------------------  LOADING  -------------------------*/
	function cachedScript(url,done){
		$.ajax({
 			type : "GET",
			dataType: "script",
			cache:  typeof(jQuery) === "function",
			url: url,
			success: done 
		});
	}
	function loadMemberAuth(){
		cachedScript(SOURCE_REF + 'assets/jslib/memberAuth.js',function(){
			memberAuthLoad = true;
		});
	}
	function loadEzModal(){
		cachedScript(SOURCE_REF + 'assets/jslib/ezModal.js',function(){
			ezModalLoad = true;
		});
	}
	function loadEmailAutocomplete(){
		if (typeof ezAutocomplete !== 'function')
            cachedScript(SOURCE_REF + 'assets/jslib/autocomplete.js');
	}
	function loadEzVertify(){
		cachedScript(SOURCE_REF + 'assets/jslib/ezVertify.js',function(){
			ezVertifyLoad = true;
		});
	}
	/*-------------------------  MODAL CONTROL  -------------------------*/
	function backdropDynamic(option){
		if(typeof jQuery === "function"){
			var backdropControl = (option == "unlock") ? "true" : "static";
			var keyboardControl = (option == "unlock") ? "true" : "false";
			var modalId = $("#ezModal-main").closest(".G-ezModal").attr("id");
			$("#" + modalId).data('bs.modal').options.backdrop = backdropControl;
			$("#" + modalId).data('bs.modal').options.keyboard  = keyboardControl;
		}
	}
	function newEzModal(){
		ezLogin.ezModal = ezLogin.ezModal || new EzModal({
			id : "ezModal-main",
			onHidden : function(){
				bindEvent("off");
			}
		});
	}
	function canEmailAutocomplete(){
		return (
            typeof ezAutocomplete === 'function' && ezModal_layoutEnv() === 'PC'
        );
	}
	function useEmailAutocomplete(inputID){
		if (canEmailAutocomplete())
            new ezAutocomplete({
                email: {
                    input: '#' + inputID,
                    origin: 'member',
                },
            });
	}
	//提供給外部共用屬性的部份
	this.origin=""; //預設是 HEADER (另外一個是ORDER)
	this.workspace={}; //暫存參數放這邊
	this.$id = function(id){ //回傳jquery
		if(id=="personId_login")return $("#ezModal-main-login-personId");
		if(id=="personId_reg")return $("#ezModal-main-reg-personId");
		if(id=="password_login")return $("#ezModal-login-password");
		if(id=="password_reg")return $("#ezModal-reg-password");
		if(id=="personId_resetPsw")return $("#ezModal-main-resetPsw-personId");
		if(id=="autoLogin")return $("#ezModal-main-autoLogin");
		if(id=="captchaImg")return $("#ezModal-captchaImg");
		if(id=="captchakey")return $("#ezModal-captchakey");
		if(id=="captchaResponse")return $("#ezModal-captcha-response");
		if(id=="socialTokenId")return $("#ezModal-socialTokenId");
		if(id=="nonMem_email")return $("#ezModal-main-nonMember");
	}
	//提供外部呼叫方法的部份
	this.ezModal ;//EzModal.js 建構使用(外部也可直接取用)
	this.onComplete = function(){}

	/*-------------------------  ERROR CONTROL  -------------------------*/
	this.captchaImgCheckError=function(message){// 驗證碼錯誤
		ezLogin.setErrorrHtml("ezModal-captcha-response",message,"change_blank");
		$("#ezModal-captcha-response").trigger("focus").trigger("select");
		$("#ezModal-captcha-response").closest(".ezModal-error-input").addClass("noClear");
		$("#ezModal-captcha-response_error").addClass("noClear");
	}
	this.loginFail=function(errMsg){// 登入失敗時錯誤訊息
		errMsg = errMsg || "登入失敗，請確認帳號與密<nobr>碼</nobr>後再重新登入";
		if(!ezLogin.workspace.captchaPwdFlow) ezLogin.setErrorrHtml("ezModal-main-login-personId","","change_blank"); // 驗證登入的輸入密碼沒有 personId 欄位
		ezLogin.setErrorrHtml("ezModal-login-password","","change_blank");
		$("#ezModal-main-error").html(errMsg).show();
		genCaptchaImg();
	}
	this.captchaLoginFail=function(errMsg){// 驗證登入失敗時錯誤訊息
		errMsg = errMsg || "登入失敗，請確認帳號再重新登入";
		ezLogin.setErrorrHtml("ezModal-main-login-personId",errMsg,"change_blank");
	}
	this.regFail=function(errMsg){// 註冊失敗時錯誤訊息
		errMsg = errMsg || "系統忙碌中，請稍後再試";
		ezLogin.setErrorrHtml("ezModal-main-reg-personId",errMsg,"change_blank");
		ezLogin.setErrorrHtml("ezModal-reg-password","","change_blank");
	}
	this.resetPswFail=function(errMsg){// 忘記密碼檢查帳號失敗時錯誤訊息
		errMsg = errMsg || "系統忙碌中，請稍後再試";
		ezLogin.setErrorrHtml("ezModal-main-resetPsw-personId",errMsg,"change_blank");
		genCaptchaImg();
	}
	this.authCodeFail=function(errMsg){// 驗證碼錯誤訊息
		errMsg = errMsg || "系統忙碌中，請稍後再試";
		$(".ezModal-authCode-input input").addClass("ezModal-error-input");
		$("#ezModal-authCode-error").html(errMsg).show();
		changeBtnStatToNormal();
	}
	this.orderCheckFail=function(errMsg){// 訪客訂單查詢失敗時錯誤訊息
		errMsg = errMsg || "系統忙碌中，請稍後再試";
		ezLogin.setErrorrHtml("ezModal-main-nonMember", errMsg, "change_blank");
		ezLogin.setErrorrHtml("ezModal-captcha-response","","change_blank");
		changeBtnStatToNormal();
		genCaptchaImg();
	}
	this.setPswFail=function(errMsg){// 訪客訂單查詢失敗時錯誤訊息
		errMsg = errMsg || "系統忙碌中，請稍後再試";
		ezLogin.setErrorrHtml("ezModal-reset-password1","","change_blank");
		ezLogin.setErrorrHtml("ezModal-reset-password2",errMsg,"change_blank");
		changeBtnStatToNormal();
	}
	this.genCaptchaImgError=function(){// 無法取得圖片
		ezLogin.setErrorrHtml("ezModal-captcha-response","無法取得圖片，請稍後再試");
	}
	this.headerOrderBindErrHandler=function() {// 社群連結發生連線錯誤，顯示錯誤訊息
		$("#ezModal-main-error").html("登入失敗，社群連結發生連線錯誤").show();
	}

	/*-------------------------  PUBLIC FN  -------------------------*/
	this.setErrorrHtml=function(id,text,change_blank){
		changeBtnStatToNormal();
		$this=$("#"+id+"").parent();
		$("#"+id+"_error").remove();//先把原先的移除
		if(text) $this.after("<div id='"+id+"_error' class='ezModal-error-msg' >"+text+"</div>");//錯誤訊息的html

		if(id=="ezModal-captcha-response") $("#ezModal-captcha-response_error").css("width","290px");
		if(typeof(change_blank)=="string"){
			if(change_blank.indexOf("change_blank")>-1){//代表變更欄位要移除錯誤
				setTimeout(function(){
					$("#"+id).off("input.ezModal-setErrorr").on("input.ezModal-setErrorr",function(){
						typeof jQuery === "function"
							? ezLogin.clearError(id,"fade")//第二個參數可加fade
							: ezLogin.clearError(id)
						$(this).off("input.ezModal-setErrorr");
					})
				},5);
			}
		}
		$("#"+id+"_error").show();
		$("#"+id+"").parent().addClass("ezModal-error-input").closest(".ezModal-input-group").addClass("ezModal-error");//錯誤訊息加到他的上一層和input-group
	}
	this.clearError=function(id,fade){
		if(id==="ALL"){
			$(".ezModal-error-input:not(.noClear)").removeClass("ezModal-error-input").closest(".ezModal-input-group").removeClass("ezModal-error");
			$(".ezModal-error-msg").hide();
			$(".ezModal-error-msg:not(.noClear)").remove();
		}else{//指定id
			$("#"+id).closest(".ezModal-error-input").removeClass("ezModal-error-input").closest(".ezModal-input-group").removeClass("ezModal-error");
			if(fade==="fade"){
				$("#"+id+"_error").fadeOut(150);
			}else{
				$("#"+id+"_error").hide();
			}
		}
	}
	this.popup=function(url, name, w, h) {
		w = w || "575";
		h = h || "600";
		// Fixes dual-screen position                         Most browsers      Firefox
		var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : window.screenX;
		var dualScreenTop = window.screenTop != undefined ? window.screenTop : window.screenY;

		var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
		var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

		var left = ((width / 2) - (w / 2)) + dualScreenLeft;
		var top = ((height / 2) - (h / 2)) + dualScreenTop;
		var newWindow = window.open(url, name, 'resizable=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

		// Puts focus on the newWindow
		if (window.focus) newWindow.focus();
	}
	this.authCodeCount=function(){
		$(".ezModal-resendAuthCodeBtn").prop("disabled", true).html("重送驗證碼 60").css("width", "115px");
		$(".ezModal-authCode-hintLink").css("display", "none");
		var i = 59;
		var count = setInterval(function(){
			if($(".ezModal-resendAuthCodeBtn").length > 0){
				$(".ezModal-resendAuthCodeBtn").html("重送驗證碼 "+i);
				i -= 1;
				if(i < 0){
					$(".ezModal-resendAuthCodeBtn").prop("disabled",false).html("重送驗證碼").css("width", "auto");
					$(".ezModal-authCode-hintLink").css("display", "block");
					clearInterval(count);
				}
			}else{
				clearInterval(count);
			}
		}, 1000);
	}
	this.authCodeCount_errMsg=function(sec, action){
		$(".ezModal-systemBusy span").html(sec);
		var i = sec-1;
		var count = setInterval(function(){
			if($(".ezModal-systemBusy span").length){
				$(".ezModal-systemBusy span").html(i);
				i -= 1;
				if(i < 0){
					if(action == "REG") ezLogin.moveLoginModal("reg");
					if(action == "FORGOT_PASS") ezLogin.moveResetPswModal1();
					if(action == "nonMember") ezLogin.moveLoginModal("nonMember");					
					clearInterval(count);
				}
			}else{
				clearInterval(count);
			}			
		}, 1000);
	}
	this.mainAuthCodeUserHtml=function(maskInfo){
		$(".ezModal-main-reg-authCode-email").html("驗證碼已發送至<br>" + maskInfo);
	}

	/*-------------------------  STORE PAGE  -------------------------*/
    // detect webview return boolean
    this.detect_inApp=function(){
		var userAgent = window.navigator.userAgent.toLowerCase();
		if(userAgent.indexOf('fb') > -1 || userAgent.indexOf('twitter') > -1 || userAgent.indexOf('line') > -1 || userAgent.indexOf('messenger') > -1 || userAgent.indexOf('wechat') > -1 || userAgent.indexOf('instagram') > -1 || userAgent.indexOf('whatsapp') > -1 || userAgent.indexOf('slack') > -1 || userAgent.indexOf('dcard') > -1 || userAgent.indexOf('ezappwebview') > -1) return true;
		else return false;
		}
	this.noWindowSocialLogin=function(){
		try {
			if (ezModal_layoutEnv() === 'MWEB') { document.cookie = "COMMON_LAYOUT_ENV=MWEB; max-age=900;domain=.eztravel.com.tw;path=/"}
			return (ezModal_layoutEnv() === 'MWEB') || ezLogin.detect_inApp();
		} catch (error) {
			console.log(error)
		}
	}
	/**
	 * 儲存瀏覽資料
	 */
	this.storeBrowseInfo = function (browseInfo, targetURL){
		// 全域docmuent.domain , 可以解決cors問題
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
		var COMMON_USERAUTH_ENDPOINT = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT;
		$.get(COMMON_USERAUTH_ENDPOINT + 'auth/genBrowseNo?' + new Date().getTime(), function (browseNo) {
			
			// 產生目前瀏覽資料 
			var browseData = {
				"browseNo": browseNo,
                "info": JSON.stringify(browseInfo.info) || "",
				"redirectURL": browseInfo.redirectURL || window.location.href,
				"cancelURL": browseInfo.cancelURL || window.location.href,
				"origin": browseInfo.origin || "HEADER"
			};
			$.ajax({
				type: "POST",
				dataType: "json",
				data: browseData,
				cache: false,
				async: false,
				xhrFields: { withCredentials: true },
				url: COMMON_USERAUTH_ENDPOINT + 'auth/saveBrowseInfo',
				success: function (data) {
					location.href = targetURL;
				}
			});
		});
	}
	var judgeObj = function (obj){
		for(var item in obj){
			return true
		}
		return false
	}
  	// 儲存瀏覽資料
	this.genBrowseInfo = function(targetURL){
		// 全域docmuent.domain , 可以解決cors問題
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
		var COMMON_USERAUTH_ENDPOINT = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT;
		if(ezLogin.workspace && ezLogin.workspace.mweb_browseInfo) {
			$.ajax({
				type: "POST",
				dataType: "json",
				data: ezLogin.workspace.mweb_browseInfo,
				cache: false,
				async: false,
				xhrFields: {withCredentials: true},
				url: COMMON_USERAUTH_ENDPOINT + 'auth/saveBrowseInfo',
				success: function(data){
					location.href = targetURL;
				}
			});
		} else {
			if (judgeObj(ezLogin.workspace.browseInfo) === false){
				var returnURL = ($(".member-eMoney-slogan").length > 0 && ezModal_layoutEnv() === 'PC') ? "https://www.eztravel.com.tw" : window.location.href;
				var browseInfo = {
					"info": "",
					"redirectURL": returnURL,
					"cancelURL": returnURL,
					"origin": "MEM_clear"//回socialAuth　→　clean browseInfo
				};
				ezLogin.storeBrowseInfo(browseInfo,targetURL)
			}else{
				if (ezModal_layoutEnv() === 'MWEB' && ezLogin.workspace.browseInfo) { document.cookie = "SOCIAL_LOGIN=PROGRESS; max-age=300;domain=.eztravel.com.tw;path=/" }
				ezLogin.storeBrowseInfo(ezLogin.workspace.browseInfo, targetURL)
			}
		}
	}
	
	/*-------------------------  MOVEMODAL  -------------------------*/
	this.moveLoginModal=function(options, unlock){
		bindEvent("on", "main");
      	ezLogin.ezModal.moveModal(loginModalBlock(),"500px");
		unlock && backdropDynamic("unlock");
		options && tabSwitch(options,true);	// reg or nonMember
		document.querySelectorAll('.email-autocomplete').forEach(function(ele){
			useEmailAutocomplete(ele.id)
		  })
      	setTimeout(genCaptchaImg,5);//延遲加載，不然圖片可能會取不到
	}
	this.moveLoginPwdModal = function(){
		bindEvent("on", "captchaPwd");
		ezLogin.ezModal.moveModal(captchaLoginPwdBlock(),"auto"); 
		setTimeout(genCaptchaImg,5);//延遲加載，不然圖片可能會取不到
	}
	this.moveLoginVerifyModal = function(){
		bindEvent("on", "vertify")
		ezLogin.ezModal.moveModal(loginVerifyBlock(),"307px"); 
		backdropDynamic("lock");
	}
	this.moveLoginSendCodeModal = function(loginType){
		bindEvent("on", "captcha2Code");
		ezLogin.ezModal.moveModal(captchaSendCode(loginType),"456px")
		backdropDynamic("lock");	 
	}
	this.moveMainAuthCodeModal=function(type, options){
		var bindOptions = "";
		if(options == "reg") bindOptions = "regAuthCode";
		if(options == "nonMember") bindOptions = "nonMemAuthCode";
		bindEvent("on", bindOptions);
		ezLogin.ezModal.moveModal(mainAuthCodeModalBlock(type, options),"480px");
		backdropDynamic("lock");
	}
	this.moveOauthModal=function(email,tokenId){
			bindEvent("on", "oauth");
      ezLogin.ezModal.moveModal(oauthModalBlock(email,tokenId),"430px");
      setTimeout(genCaptchaImg,5);//延遲加載，不然圖片可能會取不到
	}
	this.moveFinalModal=function(title,message,modalAutoHeight){
      modalAutoHeight = modalAutoHeight || "400px";
      ezLogin.ezModal.moveModal(finalModalBlock(title,message),modalAutoHeight);
	}
	this.moveResetPswModal1=function(){
			bindEvent("on", "resetPsw1");
			ezLogin.ezModal.moveModal(resetPswStep1Block(),"420px");
			setTimeout(genCaptchaImg,5);//延遲加載，不然圖片可能會取不到
	}
	this.moveResetPswModal2=function(email){
			bindEvent("on", "resetPsw2");
			ezLogin.ezModal.moveModal(resetPswStep2Block(email),"445px");
			backdropDynamic("lock");
	}
	this.moveResetPswModal3=function(){
			bindEvent("on", "resetPsw3");
			ezLogin.ezModal.moveModal(resetPswStep3Block(),"390px");
			backdropDynamic("lock");
	}
	this.moveResetPswModal4=function(){
			ezLogin.ezModal.moveModal(resetPswStep4Block(),"379px");
			backdropDynamic("unlock");
	}
	this.moveOverloadModal=function(type, minutes){
		ezLogin.ezModal.moveModal(authCodeOverloadBlock(type, minutes),"333px");
		backdropDynamic("unlock");
	}
	this.moveAuthCodeFailedModal=function(type){
		ezLogin.ezModal.moveModal(authCodeFailedBlock(type),"333px");
		backdropDynamic("unlock");
	}
	this.moveSystemBusyModal=function(errMsgSec){
		ezLogin.ezModal.moveModal(systemBusyBlock(errMsgSec),"300px");
		backdropDynamic("unlock");
	}
	this.moveSocialRegModal=function(socialBaseInfo){
		bindEvent("on", "socialRegBind");
		ezLogin.ezModal.moveModal(socialReg(socialBaseInfo), "auto");
		// 有 modal 時，背景將被鎖住
		if (!checkModalHide()) backdropDynamic("lock");
	}
	this.moveSocialBindModal=function(){
		bindEvent("on", "socialBindBind");
		ezLogin.ezModal.moveModal(socialBind(), "440px");
		setTimeout(genCaptchaImg,5);//延遲加載，不然圖片可能會取不到
	}
	// Apple 隱私信箱
	// this.moveSocialPrivateModal=function(){
	// 	bindEvent("on", "socialPrivate");
	// 	ezLogin.ezModal.moveModal(socialPrivate(), "345px");
	// }
	this.hide=function(){
		ezLogin.ezModal.hide();
	}
	this.show=function(_options,browseInfo){
		if (!!browseInfo){
			ezLogin.workspace.browseInfo = browseInfo;
		}
		if(memberAuthLoad && ezModalLoad && ezVertifyLoad){//資源加載完
			_options=_options || {};
			browseInfo = browseInfo||{};
			ezLogin.onComplete = _options.onComplete || ezLogin.onComplete;
			ezLogin.origin = _options.origin || "HEADER";
			headerName = _options.headerName || "登入" ; //沒傳值進來就設成登入
			favoriteDesc = _options.favoriteDesc || false ; // 控管 收藏前請登入的敘述 class
			claimDesc = _options.claimDesc || false;
			ezLogin.disB2vLogin = _options.disB2vLogin || false ; // 是否隱藏訪客登入的 tab
			ezLogin.forceSwitch = _options.forceSwitch || false;  // 登入強制走 loginSuccess
			if (window.ezModalShowCallback) ezModalShowCallback();						// 開 window.fun 允許 callback
			setApiEnv();
			newEzModal();
			getLoginUserData(function(data) {
				if(data == '' || data == 'B2V'|| data=='O2V' || data=='ERP'){//尚未登入、訪客視為非登入狀態
					cachedScript(SOURCE_REF + 'assets/jslib/rsa.js');
					switch(_options.page){
						case 'reg':
							ezLogin.moveLoginModal("reg");
							break;
						case 'resetPsw':
							ezLogin.moveResetPswModal1();
							break;
						case 'nonMember':
							ezLogin.moveLoginModal("nonMember");
							break;
						case 'socialReg001'://當有socialBaseInfo時才執行
							var socialBaseInfo = ezLogin.workspace.socialBaseInfo;
							socialBaseInfo && ezLogin.moveSocialRegModal(socialBaseInfo);
							break;
						case 'socialReg002'://當有socialBaseInfo時才執行
							var socialBaseInfo = ezLogin.workspace.socialBaseInfo;
							socialBaseInfo && ezLogin.moveOauthModal(socialBaseInfo.email, socialBaseInfo.tokenId);
							break;
						case 'socialFailed'://社群登入失敗
							var socialBaseInfo = ezLogin.workspace.socialBaseInfo;
							socialBaseInfo && ezLogin.moveFinalModal("社群登入失敗", socialBaseInfo.message.replace(/%w/g, "<br>"),"420px");
							break;
						default: ezLogin.moveLoginModal();
					}
					ezLogin.ezModal.show();
					 switch(_options.page){
					 	case 'socialReg001':						
					 		backdropDynamic("lock");
					 		break;
					 	default: 
							backdropDynamic("unlock");
							break;
					 }
				} else {
					orderHeaderMidStep(ezLogin.origin);
				}
			})
		} else {//資源加載中
			setTimeout(function () {
				ezLogin.show(_options, browseInfo);
			}, 300);
		}
	}
    //偵測目前瀏覽器資訊
	this.browser = function browserDetail() {
		var browser = {};
		try {
			var ua= navigator.userAgent, tem,
			M = ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
			// IE8~IE10
			if(/MSIE/i.test(M[1])){
				tem = /MSIE +(\d+)/g.exec(ua);
				browser.name = 'IE';
				browser.ver = tem[1];
				return browser;
			}
			// IE11
			if(/trident/i.test(M[1])){
				tem=  /\brv[ :]+(\d+)/g.exec(ua) || [];
				browser.name = 'IE';
				browser.ver = tem[1];
				return browser;
			}
			// Edge / Opera
			if(M[1]=== 'Chrome'){
				tem= ua.match(/\b(OPR|Edge?)\/(\d+)/);
				if(tem!= null) {
				  var info =tem.slice(1).join(' ').replace('OPR', 'Opera').replace('Edg ', 'Edge ');
				  info = info.split(" ");
				  browser.name = info[0];
				  browser.ver = info[1];
				  return browser;
				}
			}
			// Chrome / Firefox / Safari
			M = M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];
			if((tem= ua.match(/version\/(\d+)/i))!= null) M.splice(1, 1, tem[1]);
			browser.name = M[0];
			browser.ver = M[1];
			return browser;
		} catch (e) {
		  browser.name = 'NONE';
		  browser.ver = '-1';
		  return browser;
		} finally {
		  browser.secure = window.location.protocol.indexOf('s') >= 0;
		  browser.ua = navigator.userAgent;
		}
	}
	// 社群登入檢測
	function socialLoginHandler (event){
	    if (event.origin.indexOf('eztravel.com.tw')<0) {
	      console.warn('not eztravel domain. stop.');
		  return;
	    }
		try{
			if(event.data.action === 'socialAuthLoginDone'){
				var socialBaseInfo = JSON.parse(event.data.socialBaseInfo);
				var socialAction = socialBaseInfo.socialAction;
			    if(socialAction === 'REG' || socialAction === 'LOGIN'){
			      window.SocialNextStep(socialBaseInfo);
			    } else if(socialAction === 'BIND') {
			      window.SocialMemBindNextStep(socialBaseInfo);
			    }
			    document.getElementById("ezDetectLogin").remove();
			}			
		} catch (error){
			console.warn('message socialAuthLoginDone income error');
			console.warn(error);
		}
	}
	// 社群登入檢測
	function addSocialLoginListener(){
		var ezDetectLogin = document.getElementById("ezDetectLogin");
		if(ezDetectLogin!==null){
		   console.log('detectLogin is exists. stop inject again.');
		   return;
		}
		// div
		var divEzDetectLogin = document.createElement("div");
		divEzDetectLogin.id = "ezDetectLogin";
		divEzDetectLogin.style = "visibility:hidden";
		// iframe
		var iframeEzDetectLogin = document.createElement("iframe");
		iframeEzDetectLogin.id = "iframe-ezDetectLogin";
		iframeEzDetectLogin.src = ApiEndpointConfig.COMMON_USERAUTH_ENDPOINT + "auth/pushmessage/detectLogin";
		divEzDetectLogin.appendChild(iframeEzDetectLogin);
		document.body.appendChild(divEzDetectLogin);
		document.addEventListener('load', function(event){
		        var elm = event.target;
		        if( elm.id === 'iframe-ezDetectLogin'){ // or any other filtering condition
		        	// 推送監聽window來源
		            document.getElementById('iframe-ezDetectLogin').contentWindow.postMessage('origin add detectLogin','*');
		            // 開啟監聽事件
					window.addEventListener('message', socialLoginHandler, true);
		        }
		    },
		    true // Capture event
		);
	}
	//載入資源
	loadMemberAuth();
	loadEzModal();
	loadEmailAutocomplete();
	loadEzVertify();
}();