import {Component} from '@angular/core';
import { SocialSharing } from '@ionic-native/social-sharing';

@Component({
	selector : 'tgcWork',
	templateUrl : 'how_it_works.html'
})

export class TGCWorks {
	
	tabBarElement: any;

	constructor(private socialSharing: SocialSharing) {
		this.tabBarElement = document.querySelector('.tabbar.show-tabbar');
	}

	share(){
		this.socialSharing.share('Hi. I just found: How It Works in the TGC app. http://platform.createmobileapp.net/application/device/downloadapp/app_id/291').then(() => {
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