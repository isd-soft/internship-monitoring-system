import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MainPageRoutingModule } from "./main-page.routing.module";
import { CardsComponent } from "./cards/cards.component";

@NgModule({
  declarations: [CardsComponent],
  imports: [CommonModule, MainPageRoutingModule],
  exports: [CardsComponent],
})
export class MainPageModule {}
