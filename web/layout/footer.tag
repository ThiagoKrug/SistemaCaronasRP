<%@ tag body-content="empty" description="Arquivo de TAG do Footer" %>

<footer class="container">
    <nav>
        <ul>
            <li>&copy; Copyright 2013. All rights reserved.</li>
        </ul>
    </nav>
    <p>Desenvolvido por alunos do curso de Engenharia de Software da UNIPAMPA.</p>
</footer>

<script src="js/plugins/waypoints.min.js"></script>
<script src="js/bootstrap/bootstrap-tooltip.js"></script>
<script src="js/bootstrap/bootstrap-dropdown.js"></script>
<script src="js/bootstrap/bootstrap-collapse.js"></script>
<script src="js/bootstrap/bootstrap-alert.js"></script>
<script src="js/bootstrap/bootstrap-button.js"></script>
<script src="js/bootstrap/bootstrap-popover.js"></script>

<script src="js/plugins/jquery.maskedinput.min.js"></script>
<script>
    jQuery(document).ready(function() {
        jQuery(".mask_ano").mask("9999");
        jQuery(".mask_capacidade").mask("99");
        jQuery(".mask_placa").mask("aaa-9999");
        jQuery(".mask_chassi").mask("*****************");
        jQuery(".mask_renavam").mask("99999999999");
        jQuery(".mask_data").mask("99/99/9999");
        jQuery(".mask_hora").mask("99:99");
    });
</script>

<script src="js/plugins/datepicker/bootstrap-datepicker.js" charset="UTF-8"></script>
<script>
    jQuery(document).ready(function() {
        jQuery(".datepicker").datepicker({
            format : 'dd/mm/yyyy'
        });
    });
</script>

<script src="js/plugins/dataTables/jquery.datatables.min.js" charset="UTF-8"></script>
<script>
    /* Default class modification */
    $.extend( $.fn.dataTableExt.oStdClasses, {
        "sWrapper": "dataTables_wrapper form-inline"
    } );
			
    /* API method to get paging information */
    $.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
    {
        return {
            "iStart":         oSettings._iDisplayStart,
            "iEnd":           oSettings.fnDisplayEnd(),
            "iLength":        oSettings._iDisplayLength,
            "iTotal":         oSettings.fnRecordsTotal(),
            "iFilteredTotal": oSettings.fnRecordsDisplay(),
            "iPage":          Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
            "iTotalPages":    Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
        };
    }
			
    /* Bootstrap style pagination control */
    $.extend( $.fn.dataTableExt.oPagination, {
        "bootstrap": {
            "fnInit": function( oSettings, nPaging, fnDraw ) {
                var oLang = oSettings.oLanguage.oPaginate;
                var fnClickHandler = function ( e ) {
                    e.preventDefault();
                    if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
                        fnDraw( oSettings );
                    }
                };
			
                $(nPaging).addClass('pagination').append(
                '<ul>'+
                    '<li class="prev disabled"><a href="#"><span class="icon-caret-left"></span> '+oLang.sPrevious+'</a></li>'+
                    '<li class="next disabled"><a href="#">'+oLang.sNext+' <span class="icon-caret-right"></span></a></li>'+
                    '</ul>'
            );
                var els = $('a', nPaging);
                $(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
                $(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
            },
			
            "fnUpdate": function ( oSettings, fnDraw ) {
                var iListLength = 0;
                var oPaging = oSettings.oInstance.fnPagingInfo();
                var an = oSettings.aanFeatures.p;
                var i, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);
			
                if ( oPaging.iTotalPages < iListLength) {
                    iStart = 1;
                    iEnd = oPaging.iTotalPages;
                }
                else if ( oPaging.iPage <= iHalf ) {
                    iStart = 1;
                    iEnd = iListLength;
                } else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
                    iStart = oPaging.iTotalPages - iListLength + 1;
                    iEnd = oPaging.iTotalPages;
                } else {
                    iStart = oPaging.iPage - iHalf + 1;
                    iEnd = iStart + iListLength - 1;
                }
			
                for ( i=0, iLen=an.length ; i<iLen ; i++ ) {
			
                    // Add / remove disabled classes from the static elements
                    if ( oPaging.iPage === 0 ) {
                        $('li:first', an[i]).addClass('disabled');
                    } else {
                        $('li:first', an[i]).removeClass('disabled');
                    }
			
                    if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
                        $('li:last', an[i]).addClass('disabled');
                    } else {
                        $('li:last', an[i]).removeClass('disabled');
                    }
                }
            }
        }
    });
			
    /* Table #example */
    $(document).ready(function() {
        $('.datatable').dataTable( {
            "sDom": "<'row'<'span4'l><'span4'f>r>t<'row'<'span4'i><'span4'p>>",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_ registros por página"
            }
        });
    });
    
    /* Show/hide table column */
    function dtShowHideCol( iCol ) {
        var oTable = $('#example-2').dataTable();
        var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
        oTable.fnSetColumnVis( iCol, bVis ? false : true );
    };
			
    /* Table #example */
    $(document).ready(function() {
        $('.datatable-controls').on('click','li input',function(){
            dtShowHideCol( $(this).val() );
        })
    });
</script>