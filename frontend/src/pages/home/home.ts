import {Component, signal} from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  private _name = signal('')
  public name = this._name.asReadonly()

  public isValid() {
    return this._name().trim().length > 0
  }

  public nameIgnore: string = "timmy turner"
}
