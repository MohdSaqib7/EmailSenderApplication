import { Component, OnInit} from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BackendService } from '../../services/backend.service';


@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrl: './email.component.css'
})
export class EmailComponent implements OnInit {

  data={
    emailAdd:"",
    emailPass:"",
    to:"",
    subject:"",
    message:""
  }

  flag=false;

  constructor(private backendService:BackendService, private snak:MatSnackBar ) {
  }

  ngOnInit(): void {
  }

  doSubmitForm()
  {
    console.log("DATA ",this.data);
    if(this.data.emailAdd=='' || this.data.emailPass==''|| this.data.to=='' || this.data.subject==''|| this.data.message=='')
    {
      this.snak.open("fields can not be empty !!","OK");
      return;
    }

    this.flag=true;
    // this.backendService.sendEmail(this.data).subscribe((response)=>{
    //   console.log(response);   
    //   this.flag=false; 
    //   this.snak.open("Send Success ","OK")  
    // },
    // error=>{
    //   console.log(error); 
    //   this.flag=false;    
    //   this.snak.open("ERROR!! ","OK")   
    // }) 
    this.backendService.sendEmail(this.data).subscribe({
      next:response=>{
        console.log(response);
        this.flag=false;
        this.snak.open("Send Success ","OK") 
      }, 
      error:e=>{
        console.log(e); 
        this.flag=false;    
        this.snak.open("ERROR!! ","OK") 
      }
    })
  
  }

}
