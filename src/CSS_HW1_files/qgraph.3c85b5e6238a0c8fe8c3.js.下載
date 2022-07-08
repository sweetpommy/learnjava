window.QGSettings = {
   "origin": "http://test.eztravel.com.tw/hp/",
   "personalizationEnabled": true,
   "vapidPublicKey": "BIvNL8TxXa48lp0b_3TfsVxIM1OIS1jzsG3WO_92dJadkY4N90f2pS5TNRtGgTVPrQsMYtDoFh6G7Ko0_eWiKlk",
   "inWebEnabled": true,
   "qgendpoint": "https:///notify.html",
   "appId": "3c85b5e6238a0c8fe8c3",
   "debug": false
};
if(window.qg && window.qg.queue) {
    window.qg.queue.unshift(('init', QGSettings));
}
!function(q,g,r,a,p,h,js){
    if(!q.qg){
        js=q.qg=function() {
            js.callmethod ? js.callmethod.call(js, arguments) : js.queue.push(arguments);
            js.queue = [];
        }
    }
    if(q.qg.initialized){return;}
    window.qg.queue.unshift(['init',window.QGSettings])
    p=g.createElement(r);
    p.async=!0;
    p.src=a;
    h=g.getElementsByTagName(r)[0];
    h.parentNode.insertBefore(p,h);
    q.qg.initialized = true;
}(window,document,'script','//cdn.qgraph.io/v3/r/aiqua.js');

