import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `<label>Введите имя:</label>
                   <input [(ngModel)]="title" placeholder="title">
                   <h1>Добро пожаловать {{name}}!</h1>`
  })

  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'myShop';
}
