$(window).load(function () {

    jQuery.fn.toggleOption = function (show) {
        jQuery(this).toggle(show);
        if( show ) {
        if( jQuery( this ).parent( 'span.toggleOption' ).length )
            jQuery( this ).unwrap( );
    } else {
        if( jQuery( this ).parent( 'span.toggleOption' ).length ){
            
        } else {
            jQuery( this ).wrap( '<span class="toggleOption" style="display: none;" />' );
        }
    }
    };

    $('#medewerkerSelect').on('change', function () {
        var val = $(this).val();
        /* $('#functies option').prop('disabled', function(){
         return !$(this).hasClass( val)
         }); */
        $('#functies option').each(function () {
            var $option = $(this);
            $option.toggleOption($option.hasClass(val));
        });
    });
});