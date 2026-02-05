import { Component } from '@angular/core';

@Component({
  selector: 'app-libro',
  standalone: false,
  templateUrl: './libro.html',
  styleUrl: './libro.css',
})
export class LibroComponent LibroComponent OnInit{

  Libros: Libro[] = [];
  autores: Autor[] = [];
  categorias: Categoria[ = [];
  libro: Libro = { } as Libro;
  editar: boolean = false;
  idEditar: number | null = null;
  dataSource!: MaTableDataSource<Libro>;
  seleccionarArchivo!: File;
  imagenAnterior: string = "";

  mostrarColumnas: String[] = ['idLibro','titulo','editorial','edicion','idioma','fechaPublicacion','numEjemplares','precio','autor','categoria','acciones'];

  @ViewChild('formularioLibro') formularioLibro!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild('modalLibro') modallLibro!: TemplateRef<any>;

  constructor(){
    private libroService: Libroservice,
    private autorService: AutorService,
    private categoriaService: categoriaService,
    private dialog: MatDialog,
    private http: HttpClient
    }

  ngOnInit(): void {
    this.findAll();
    this.cargarAutores();
    this.cargarCategorias();
  }

  findAll(): void{
    this.libroService.findAll().subscribe(data=> {
      this.data = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

  cargarAutores(): void{
    this.autorService.findAll().subscribe(data => {
      this.autores = data;
      })
  }
}

  cargarCategorias(): void{
    this.categoriaService.findAll().subscribe(data => {
      this.categoria = data;
      })
  }

  save(): void{
    this.libroService.save(this.libro).subscribe(()=> {
      this.libro = {} as Libro;
      this.editar = false;
      this.idEditar = null;
      this.findAll();
    });
  }

  update():void{
    if(this.editar!== null){
      this.libroService.uodate(this.idEditar, this.libro).subscribe(() =>{
      this.libro = {} as Libro;
      this.editar = false;
      this.idEditar = null;
      this.findAll();
      });
    }
  }


}
