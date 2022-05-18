import { Component, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'fleet-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss'],
})
export class NavigationBarComponent {

  @Output()
  toggleSidenav = new EventEmitter<void>();

  public onProfile() {
    console.log('onProfile()');
  }

  public logout() {
    console.log('logout()');
  }

}
