import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { InAppBrowser } from '@ionic-native/in-app-browser';
import { SocialSharing } from '@ionic-native/social-sharing';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { AboutPage } from '../pages/about/about';
import { TGCHome } from '../pages/tgcHome/tgcHome';
import { BookNow } from '../pages/bookNow/bookNow';
import { TGCWorks } from '../pages/how_it_works/how_it_works';
import { More } from '../pages/more/more';
import { WhatsNew } from '../pages/whatsNew/whatsNew';
import { TabsPage } from '../pages/tabs/tabs';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

@NgModule({
    declarations: [

    MyApp,
    ContactPage,
    HomePage,
    AboutPage,
    BookNow,
    TGCHome,
    TGCWorks,
    More,
    WhatsNew,
    TabsPage
    ],

    imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp)
    ],

    bootstrap: [IonicApp],
    entryComponents: [
    MyApp,
    ContactPage,
    HomePage,
    AboutPage,
    BookNow,
    TGCHome,
    TGCWorks,
    More,
    WhatsNew,
    TabsPage
    ],

    providers: [
    StatusBar,
    SplashScreen,
    InAppBrowser,
    SocialSharing,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
    ]
})

export class AppModule {}