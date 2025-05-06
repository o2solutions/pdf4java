The `PDFToFixedImageSize` sample shows how to convert a PDF page to an image of fixed size. 
This is done by settings the `OutputImageSize` property on the `PDFRendererSettings` object. When this property is set, the `PDFRendererSettings.getDpiX()` and `PDFRendererSettings.getDpiY()` properties are ignored.
If either `getWidth()` or `getHeight()` properties of the `OutputImageSize` are set to 0, they are computed automatically to keep the original page aspect ratio.

