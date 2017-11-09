// See http://brunch.io for documentation.
exports.files = {
  javascripts: {
    joinTo: {
      'app.js': /^src/,
      'vendor.js': /^node_modules/
    }
  },
  stylesheets: {
    joinTo: {
      'bundle.css': /^src\/styles/
    }
  }
};

exports.paths = {
  // public: '../breakout-backend/web',
  public: '../android-app/Smash.It/app/src/main/assets/www',
  watched: ['src']
};

exports.plugins = {

};

exports.npm = {
  enabled: true,
  globals: {
    jQuery: 'jquery',
    $: 'jquery',
  }
};
