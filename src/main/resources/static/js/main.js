let loaded = 0, toLoad = 1;

function loadStep() {
    loaded++;

    if (loaded >= toLoad) {
        lock.hide();
        $("#app").attr('loaded', 'true');
        $(".determinate").show("slide", { direction: "left" }, 100);
        $(".tabs > .tab:first-child > a").click();
    }
}

application = new Vue({
    el: '#app',
    data: {
        user: {
            id:'',
            email:'',
            name:'',
            picture:''
        }
    }
});

href = window.location.href;
token = href.substring(href.indexOf("=")+1,href.indexOf("&"));

var lock = new Auth0Lock(
    '8neJIfOnnW5261ekwznGbvb65r9Zd7PR',
    'two-one-three-seven.eu.auth0.com',
    {
        closable: false,
        languageDictionary: {
            title: "2137"
        },
        theme: {
            logo: 'http://alexjones.pl/media/k2/items/cache/d9825d27bb75dc7e565ea2bf8a02f095_XL.jpg',
            primaryColor: '#86ff67'
        }
    }
);

getUserData = function(profile) {
    application.user = {
        id: profile.sub,
        email: profile.email,
        picture: profile.picture,
        name: profile.name
    };
    if (loaded < toLoad) {
        application.$forceUpdate();
        loadStep();
    }
};

lock.on("authenticated", function(authResult) {
    lock.getUserInfo(authResult.accessToken, function(error, profile) {
        if (error) {
            return;
        }

        for(entry in profile["http://localhost:8080/user_metadata"]) {
            profile[entry] = profile["http://localhost:8080/user_metadata"][entry];
        }
        profile["http://localhost:8080/user_metadata"] = null;

        localStorage.setItem('accessToken', authResult.accessToken);
        localStorage.setItem('profile', JSON.stringify(profile));

        getUserData(profile);
    });
});

if (token.length !== 32) {
    token = localStorage.getItem('accessToken');
}
if (!token || token.length !== 32) {
    lock.show();
} else {
    profile = JSON.parse(localStorage.getItem('profile'));
    if (profile) {
        document.getElementById('loading').style.display = null;
        getUserData(profile);
    } /*else {
        lock.show();
    }*/
}

function logout () {
    lock.logout();
    localStorage.removeItem( 'accessToken' );
    localStorage.removeItem( 'profile' );
}
$(".button-collapse").sideNav();

