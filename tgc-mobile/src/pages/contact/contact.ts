import { Component } from '@angular/core';

@Component({
	selector: 'page-contact',
	templateUrl: 'contact.html'
})

export class ContactPage {
	
	tabBarElement: any;

	constructor( ){
		this.tabBarElement = document.querySelector('.tabbar.show-tabbar');
	}

	ionViewWillEnter(){
		this.tabBarElement.style.display = 'none';
	}

	ionViewWillLeave() {
		this.tabBarElement.style.display = 'flex';
	}
}
