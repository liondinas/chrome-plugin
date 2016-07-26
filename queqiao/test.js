/**
 * Created by rawind on 16-7-24.
 */


var FindProxyForURL = function (url, host) {
    if (shExpMatch(url, '*facebook.com')) {
        return 'SOCKS5 127.0.0.1:1080';
    }
    return 'DIRECT';
}


var FindProxyForURL = function (url, host) {
    if (dnsDomainIs(host,'.google.com'))
    {
        return 'SOCKS5 127.0.0.1:1080';
    }
    if (dnsDomainIs(host,'.chrome.com'))
    {
        return 'SOCKS5 127.0.0.1:1080';
    }
    if (dnsDomainIs(host,'.facebook.com'))
    {
        return 'SOCKS5 127.0.0.1:1080';
    }
    return 'DIRECT';
}