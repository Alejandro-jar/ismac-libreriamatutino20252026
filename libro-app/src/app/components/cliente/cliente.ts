import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Cliente } from '../../models/cliente'; 
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import swal from 'sweetalert2';
import { ClienteService } from '../../services/cliente';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { NgClass } from "../../../../node_modules/@angular/common/types/_common_module-chunk";
import { MatFormField } from "@angular/material/select";
import { MatIcon } from "@angular/material/icon";

@Component({
  selector: 'app-cliente',
  imports: [NgClass, MatFormField, MatTable, MatSort, MatIcon, MatPaginator],
  templateUrl: './cliente.html',
  styleUrl: './cliente.css',
})
export class ClienteComponent implements OnInit {
  

  @ViewChild('formularioCliente') formularioCliente!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  clients: Cliente[] = [];
  cliente: Cliente = { } as Cliente;
  editar: boolean = false;
  idEditar: number | null = null;

  dataSource!: MatTableDataSource<Cliente>;
  mostrarColumnas: string[] = ['idCliente', 'cedula', 'nombre', 'apellido', 'telefono', 'correo', 'acciones'];

  constructor(private clienteService: ClienteService) { }

    ngOnInit(): void {
      throw new Error('Method not implemented.');
    }

    findAll(): void {
      this.clienteService.findAll().subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      });
  }

  save(): void {
    this.clienteService.save(this.cliente).subscribe(() => {
      this.cliente = { } as Cliente;
      this.findAll();
  });
  }

  update(): void {
    if (this.idEditar !== null) {
      this.clienteService.update(this.idEditar, this.cliente).subscribe(() => {
        this.cliente = { } as Cliente;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(): void {
    //this.clienteService.delete(this.client.id).subscribe(() => {});
    swal.fire({   
    title: '¿Desea aeliminar el dato?',
    text: 'Esta acción no se puede deshacer.',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Sí, eliminar',
    confirmButtonColor: '#3085d6',
    cancelButtonText: 'Cancelar',
    cancelButtonColor: '#d33',
    }).then((result) => {
    if (result.isConfirmed) {
      this.clienteService.delete(this.cliente.idCliente).subscribe(() => {
        this.findAll();
        this.cliente = { } as Cliente;
        Swal.fire('Eliminado!', 'El dato ha sido eliminado.', 'success');
      });
     }else {  
      this.cliente = { } as Cliente;
     }
    });
  }


  //interacción en la pagina web

  editarCliente(cliente: Cliente): void {
    this.cliente = { ...cliente };
    this.idEditar = cliente.id;
    this.editar = true;

    setTimeout(() => {
      this.formularioCliente.nativeElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
    },100);
  }

  editarClienteCancelar(form: NgForm): void {
    this.cliente = { } as Cliente;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardar(form: NgForm): void {
    if (this.editar && this.idEditar !== null) {
      this.update();
      form.resetForm();
    } else {
      this.save();
      form.resetForm();
    }
  }

  applyFilter(event: Event){
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }


}