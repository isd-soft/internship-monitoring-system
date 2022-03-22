import { Component, OnInit } from "@angular/core";
import CARD_DISCIPLINES from "../../../../environments/disc";

@Component({
  selector: "app-cards",
  templateUrl: "./cards.component.html",
  styleUrls: ["./cards.component.css"],
})
export class CardsComponent implements OnInit {
  cards = CARD_DISCIPLINES;

  constructor() {}

  ngOnInit(): void {}
}
