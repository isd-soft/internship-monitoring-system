import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MainPageRoutingModule } from "./main-page.routing.module";
import { CardsComponent } from "./cards/cards.component";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatChipsModule } from "@angular/material/chips";

@NgModule({
  declarations: [CardsComponent],
  imports: [
    CommonModule,
    MainPageRoutingModule,
    MatGridListModule,
    MatChipsModule,
  ],
  exports: [CardsComponent],
})
export class MainPageModule {}
