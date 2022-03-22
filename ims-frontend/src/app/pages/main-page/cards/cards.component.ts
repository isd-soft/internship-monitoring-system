import { Component } from "@angular/core";
import { CARD_DISCIPLINES } from "../../../../environments/disciplines";

@Component({
  selector: "app-cards",
  templateUrl: "./cards.component.html",
  styleUrls: ["./cards.component.css"],
})
export class CardsComponent {
  cards = CARD_DISCIPLINES;
}
