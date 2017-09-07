import {Component} from '@angular/core';
import {NavController} from 'ionic-angular';
import {InAppBrowser} from '@ionic-native/in-app-browser';
import {AboutPage} from '../about/about';
import {BookNow} from '../bookNow/bookNow';
import {TGCWorks} from '../how_it_works/how_it_works';
import {WhatsNew} from '../whatsNew/whatsNew';
import {ContactPage} from '../contact/contact';

@Component({
	selector : 'more',
	templateUrl : 'more.html'
})

export class More {
	
	tabBarElement: any;

	constructor(public navCtrl:NavController,private iab: InAppBrowser) {
		this.tabBarElement = document.querySelector('.tabbar.show-tabbar');
	}

	ionViewWillEnter(){
		this.tabBarElement.style.display = 'none';
	}

	ionViewWillLeave() {
		this.tabBarElement.style.display = 'flex';
	}

	takeMeBack(){
		this.navCtrl.parent.select(0);
	}

	gotoAbout(){
		this.navCtrl.push(AboutPage);
	}

	gotoBookNow(){
		this.navCtrl.push(BookNow);
	}

	gotoHowWorks(){
		this.navCtrl.push(TGCWorks);
	}

	gotoWhatsNew(){
		this.navCtrl.push(WhatsNew);
	}

	gotoFacebook(){
		this.iab.create('https://www.facebook.com', '_blank', 'toolbar=no');
	}

	gotoInstagram(){
		this.iab.create('https://www.instagram.com/todaygotclass/', '_blank', 'toolbar=no');
	}

	contact(){
		this.navCtrl.push(ContactPage);
	}
}