import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { SocialSharing } from '@ionic-native/social-sharing';

@Component({
	selector: 'about',
	templateUrl: 'about.html'
})

export class AboutPage {
	
	tabBarElement: any;

	constructor(public navCtrl:NavController,private socialSharing: SocialSharing){
		this.tabBarElement = document.querySelector('.tabbar.show-tabbar');
	}

	share(){
		this.socialSharing.share('Hi. I just found: About in the TGC app. http://platform.createmobileapp.net/application/device/downloadapp/app_id/291').then(() => {
			console.log("success");
		}).catch(() => {
			console.log("in error");
		// Error!
		});
	}

	ionViewWillEnter(){
		this.tabBarElement.style.display = 'none';
	}

	ionViewWillLeave() {
		this.tabBarElement.style.display = 'flex';
	}
}
