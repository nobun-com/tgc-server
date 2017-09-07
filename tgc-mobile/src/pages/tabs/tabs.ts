import { Component } from '@angular/core';
import { NavController} from 'ionic-angular';
import { TGCHome } from '../tgcHome/tgcHome';
import { AboutPage } from '../about/about';
import { TGCWorks } from '../how_it_works/how_it_works';
import { BookNow} from '../bookNow/bookNow';
import { WhatsNew} from '../whatsNew/whatsNew';
import { More } from '../more/more';

@Component({
	templateUrl: 'tabs.html'
})

export class TabsPage {
	tab1Root = TGCHome;
	tab5Root = More;

	constructor(public navCtrl:NavController) {

	}

	about(){
		this.navCtrl.push(AboutPage);
	}

	howItWorks(){
		this.navCtrl.push(TGCWorks);
	}

	bookNow(){
		this.navCtrl.push(BookNow);
	}

	whatsNew(){
		this.navCtrl.push(WhatsNew);
	}
}
