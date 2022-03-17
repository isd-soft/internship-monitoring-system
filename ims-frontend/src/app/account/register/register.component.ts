import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";

import { first } from "rxjs/operators";
declare var $: any;
@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  form: FormGroup = new FormGroup({});
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    // this.form = this.formBuilder.group({
    //   username: ["", Validators.required],
    //   email: [
    //     "",
    //     [
    //       Validators.required,
    //       Validators.email,
    //       Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
    //     ],
    //   ],
    //   password1: ["", [Validators.required, Validators.minLength(8)]],
    //   password2: ["", [Validators.required, Validators.minLength(8)]],
    // });
    //
    // $(document).ready(function () {
    //   $(".input").focus(function () {
    //     $(this).parent().find(".label-txt").addClass("label-active");
    //   });
    //   $(".input").focusout(function () {
    //     if ($(this).val() == "") {
    //       $(this).parent().find(".label-txt").removeClass("label-active");
    //     }
    //   });
    // });
  }

  // get f() {
  //   return this.form.controls;
  // }
  //
  // onSubmit() {
  //   this.submitted = true;
  // a
  //   this.loading = true;
  //   console.log(this.form.value);
  // }
}
